package peipo.ru.storage.infrastructure.grpc;

import io.grpc.Server;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcServerRunner implements ApplicationRunner
{
    private static final Logger log = LoggerFactory.getLogger(GrpcServerRunner.class);

    private final Server grpcServer;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        grpcServer.start();
        log.info("gRPC server started on port {}", grpcServer.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            log.info("Shutting down gRPC server...");
            grpcServer.shutdown();
        }));
    }
}