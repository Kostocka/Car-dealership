package peipo.ru.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import peipo.ru.common.dto.cars.CarResponseDto;
import peipo.ru.common.security.KeyCloakTokenService;

public class OrderGrpcClientTest
{
    private static final String ORDER_API =
            System.getProperty(
                    "order.url",
                    "http://localhost:8082"
            );

    private final RestTemplate rest = new RestTemplate();
    private final KeyCloakTokenService auth = new KeyCloakTokenService();

    @Test
    void shouldGetCarsGrpc()
    {
        // Arrange
        String token =
                auth.getAccessToken(
                        "user1",
                        "user1"
                );

        // Act
        ResponseEntity<CarResponseDto[]> response =
                rest.exchange(
                        ORDER_API + "/api/v1/cars",
                        HttpMethod.GET,
                        new HttpEntity<>(
                                headers(token)
                        ),
                        CarResponseDto[].class
                );

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().length > 0);
    }

    private HttpHeaders headers(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
