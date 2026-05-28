package peipo.ru.integration;

import java.time.Duration;
import org.junit.jupiter.api.*;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import peipo.ru.common.dto.cars.CarResponseDto;
import peipo.ru.common.security.KeyCloakTokenService;
import peipo.ru.integration.infrastructure.DockerHelper;

import static org.awaitility.Awaitility.await;

public class DiscoveryTest
{
    private static final String ORDER_API =
            System.getProperty(
                    "order.url",
                    "http://localhost:8082"
            );

    private final RestTemplate rest = new RestTemplate();
    private final KeyCloakTokenService auth = new KeyCloakTokenService();

    @AfterEach
    void restoreStorage() throws Exception
    {
        DockerHelper.start("storage-service");
        DockerHelper.waitFor(15000);
    }

    @Test
    void shouldResolveStorageThroughDiscovery()
    {
        String token = auth.getAccessToken("user1", "user1");

        ResponseEntity<CarResponseDto[]> response =
                rest.exchange(
                        ORDER_API + "/api/v1/cars",
                        HttpMethod.GET,
                        new HttpEntity<>(headers(token)),
                        CarResponseDto[].class
                );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().length > 0);
    }

    @Test
    void shouldReconnectAfterStorageRestart() throws Exception
    {
        String token = auth.getAccessToken("user1", "user1");

        rest.exchange(
                ORDER_API + "/api/v1/cars",
                HttpMethod.GET,
                new HttpEntity<>(headers(token)),
                CarResponseDto[].class
        );

        DockerHelper.stop("storage-service");
        DockerHelper.waitFor(5000);

        Assertions.assertThrows(
                HttpServerErrorException.class,
                () -> rest.exchange(
                        ORDER_API + "/api/v1/cars",
                        HttpMethod.GET,
                        new HttpEntity<>(headers(token)),
                        String.class
                )
        );

        DockerHelper.start("storage-service");
        DockerHelper.waitFor(30000);

        await()
                .atMost(Duration.ofSeconds(30))
                .untilAsserted(() -> {
                    ResponseEntity<CarResponseDto[]> response =
                            rest.exchange(
                                    ORDER_API + "/api/v1/cars",
                                    HttpMethod.GET,
                                    new HttpEntity<>(headers(token)),
                                    CarResponseDto[].class
                            );

                    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
                    Assertions.assertNotNull(response.getBody());
                    Assertions.assertTrue(response.getBody().length > 0);
                });
    }

    private HttpHeaders headers(String token)
    {
        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}