package peipo.ru.order.infrastructure.grpc;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import peipo.ru.common.grpc.CarGrpcServiceGrpc;

@Log
@Component
@RequiredArgsConstructor
public class CarGrpcServiceFactory
{
    private final DiscoveryClient discoveryClient;
    private ManagedChannel channel;
    private CarGrpcServiceGrpc.CarGrpcServiceBlockingStub stub;

    public CarGrpcServiceGrpc.CarGrpcServiceBlockingStub getStub()
    {
        if (isAlive())
        {
            return stub;
        }

        ServiceInstance storage =
                discoveryClient
                        .getInstances("storage-service")
                        .stream()
                        .findFirst()
                        .orElse(null);

        if (storage == null)
        {
            log.warning("Storage service unavailable");
            throw new IllegalStateException("Storage service not available");
        }
        try
        {
            int grpcPort = Integer.parseInt(storage.getMetadata().get("grpc-port"));
            closeChannel();

            channel =
                    ManagedChannelBuilder
                            .forAddress(storage.getHost(), grpcPort)
                            .usePlaintext()
                            .build();
            stub = CarGrpcServiceGrpc.newBlockingStub(channel);
            log.info("Created new gRPC connection");

            return stub;
        } catch (Exception e)
        {
            log.warning("Failed to create gRPC connection: " + e.getMessage());
            closeChannel();

            return null;
        }
    }

    private boolean isAlive()
    {
        if (channel == null || stub == null)
        {
            return false;
        }

        ConnectivityState state = channel.getState(true);

        return state != ConnectivityState.SHUTDOWN && state != ConnectivityState.TRANSIENT_FAILURE;
    }

    private void closeChannel()
    {
        try
        {
            if (channel != null)
            {
                channel.shutdown();
            }

        } catch (Exception ignored)
        {
            channel = null;
            stub = null;
        }
    }
}
