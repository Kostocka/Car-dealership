package peipo.ru.order.infrastructure.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import peipo.ru.common.grpc.CarGrpcServiceGrpc;

@Configuration
@EnableConfigurationProperties(GrpcStorageProperties.class)
public class GrpcClientConfig
{
    @Bean
    public ManagedChannel storageChannel(DiscoveryClient  discoveryClient)
    {
        ServiceInstance storage =
                discoveryClient
                        .getInstances("storage-service")
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Storage service not found"));

        int grpcPort = Integer.parseInt(storage.getMetadata().get("grpc-port"));

        return ManagedChannelBuilder
                .forAddress(storage.getHost(), grpcPort)
                .usePlaintext()
                .build();
    }

    @Bean
    public CarGrpcServiceGrpc.CarGrpcServiceBlockingStub carStub(ManagedChannel channel)
    {
        return CarGrpcServiceGrpc.newBlockingStub(channel);
    }
}
