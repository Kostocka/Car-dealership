package peipo.ru.integration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import peipo.ru.common.grpc.*;
import peipo.ru.common.security.KeyCloakTokenService;

public class GrpcFailureScenariosTest
{
    private static final String ORDER_API =
            System.getProperty(
                    "order.url",
                    "http://localhost:8082"
            );

    private static final String STORAGE_GRPC_HOST =
            System.getProperty(
                    "storage.grpc.host",
                    "localhost"
            );

    private static final int STORAGE_GRPC_PORT =
            Integer.parseInt(
                    System.getProperty(
                            "storage.grpc.port",
                            "9090"
                    )
            );

    private static final String DOCKER_COMPOSE_FILE =
            System.getProperty(
                    "docker.compose.file",
                    "../docker/docker-compose.yml"
            );

    private final RestTemplate rest = new RestTemplate();
    private final KeyCloakTokenService auth = new KeyCloakTokenService();

    @AfterEach
    void restoreStorage() throws Exception
    {
        dockerCompose("start", "storage-service");
        Thread.sleep(20000);
    }

    @Test
    void shouldHandleServiceUnavailable() throws Exception
    {
        // Arrange
        dockerCompose("stop", "storage-service");
        Thread.sleep(3000);

        String token =
                auth.getAccessToken(
                        "user1",
                        "user1"
                );

        // Act
        HttpServerErrorException exception =
                Assertions.assertThrows(
                        HttpServerErrorException.class,
                        () -> rest.exchange(
                                ORDER_API + "/api/v1/cars",
                                HttpMethod.GET,
                                new HttpEntity<>(
                                        headers(token)
                                ),
                                String.class
                        )
                );

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }

    @Test
    void shouldTimeout()
    {
        // Arrange
        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress(STORAGE_GRPC_HOST, STORAGE_GRPC_PORT)
                        .usePlaintext()
                        .build();

        // Act
        try
        {
            var stub =
                    CarGrpcServiceGrpc
                            .newBlockingStub(channel)
                            .withDeadlineAfter(1, TimeUnit.MILLISECONDS);

            StatusRuntimeException exception =
                    Assertions.assertThrows(
                            StatusRuntimeException.class,
                            () -> stub.getAllCars(GetAllCarsRequest.newBuilder().build())
                    );

            // Assert
            Assertions.assertEquals(Status.DEADLINE_EXCEEDED.getCode(), exception.getStatus().getCode());
        }
        finally
        {
            channel.shutdownNow();
        }
    }

    @Test
    void shouldReturnEmptyResult()
    {
        // Arrange
        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress(STORAGE_GRPC_HOST, STORAGE_GRPC_PORT)
                        .usePlaintext()
                        .build();

        // Act
        try
        {
            var stub = CarGrpcServiceGrpc.newBlockingStub(channel);
            var response =
                    stub.getAllCars(
                            GetAllCarsRequest
                                    .newBuilder()
                                    .setFilter(
                                            CarFilterMessage
                                                    .newBuilder()
                                                    .setBrand("TU-TU-TU-TU-MaxVerstappen")
                                                    .build()
                                    )
                                    .build()
                    );

            // Assert
            Assertions.assertTrue(response.getCarsList().isEmpty());
        }
        finally
        {
            channel.shutdownNow();
        }
    }

    private void dockerCompose(String command, String service) throws IOException, InterruptedException
    {
        int code = new ProcessBuilder("docker", "compose","-f", DOCKER_COMPOSE_FILE, command, service)
                        .inheritIO()
                        .start()
                        .waitFor();

        Assertions.assertEquals(0, code);
    }

    private HttpHeaders headers(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
