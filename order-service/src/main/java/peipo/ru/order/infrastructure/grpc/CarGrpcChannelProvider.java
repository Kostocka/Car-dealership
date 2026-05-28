package peipo.ru.order.infrastructure.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.locks.ReentrantLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import peipo.ru.common.grpc.CarGrpcServiceGrpc;

@Log
@Component
@RequiredArgsConstructor
public class CarGrpcChannelProvider
{
    private final StorageInstanceResolver resolver;
    private final ReentrantLock lock = new ReentrantLock();
    private volatile ManagedChannel channel;
    private volatile CarGrpcServiceGrpc.CarGrpcServiceBlockingStub stub;
    private volatile long lastFailed;
    private final GrpcStorageProperties props;

    public CarGrpcServiceGrpc.CarGrpcServiceBlockingStub getStub()
    {
        if (isHealthy())
        {
            return stub;
        }

        if (System.currentTimeMillis() - lastFailed < props.getReconnectDelayMs())
        {
            throw new IllegalStateException("storage-service temporarily unavailable");
        }

        lock.lock();
        try
        {
            if (isHealthy())
            {
                return stub;
            }

            ServiceInstance instance = resolver.resolve();

            if (instance == null)
            {
                lastFailed = System.currentTimeMillis();
                throw new IllegalStateException("storage-service unavailable");
            }

            recreate(instance);

            return stub;

        } finally
        {
            lock.unlock();
        }
    }

    private void recreate(ServiceInstance instance)
    {
        close();

        int port = Integer.parseInt(instance.getMetadata().getOrDefault("grpc-port", "9090"));

        channel = ManagedChannelBuilder
                .forAddress(instance.getHost(), port)
                .usePlaintext()
                .build();

        stub = CarGrpcServiceGrpc.newBlockingStub(channel);

        log.info("gRPC channel recreated -> " + instance.getHost() + ":" + port);
    }

    private boolean isHealthy()
    {
        if (channel == null || stub == null)
        {
            return false;
        }

        return switch (channel.getState(true))
        {
            case READY, IDLE -> true;
            default -> false;
        };
    }

    private void close()
    {
        if (channel != null)
        {
            channel.shutdown();
        }

        channel = null;
        stub = null;
    }
}