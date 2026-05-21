package peipo.ru.order.infrastructure.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import peipo.ru.common.grpc.CarGrpcServiceGrpc;

@Configuration
@EnableConfigurationProperties(GrpcStorageProperties.class)
public class GrpcClientConfig
{
    @Bean
    public ManagedChannel storageChannel(GrpcStorageProperties props)
    {
        return ManagedChannelBuilder
                .forAddress(props.getHost(), props.getPort())
                .usePlaintext()
                .build();
    }

    @Bean
    public CarGrpcServiceGrpc.CarGrpcServiceBlockingStub carStub(ManagedChannel channel)
    {
        return CarGrpcServiceGrpc.newBlockingStub(channel);
    }
}
