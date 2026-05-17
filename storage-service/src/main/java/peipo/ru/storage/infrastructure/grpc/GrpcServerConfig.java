package peipo.ru.storage.infrastructure.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GrpcServerConfig
{
    private final CarGrpcServiceImpl carGrpcServiceImpl;

    @Value("${grpc.port}")
    private int grpcPort;

    @Bean
    public Server grpcServer()
    {
        return ServerBuilder
                .forPort(grpcPort)
                .addService(carGrpcServiceImpl)
                .build();
    }
}
