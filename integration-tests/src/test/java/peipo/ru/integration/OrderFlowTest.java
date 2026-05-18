package peipo.ru.integration;

import java.time.Duration;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import peipo.ru.common.dto.ConfiguredCarRequest;
import peipo.ru.common.dto.cars.CarConfigurationDto;
import peipo.ru.common.dto.cars.CarResponseDto;
import peipo.ru.common.dto.orders.ConfiguredCarOrderDto;
import peipo.ru.common.dto.orders.CreateConfiguredOrderRequest;
import peipo.ru.common.dto.orders.CreateStockOrderRequest;
import peipo.ru.common.dto.orders.StockCarOrderDto;
import peipo.ru.common.dto.parts.requests.AddStockRequest;
import peipo.ru.common.security.KeyCloakTokenService;

import static org.awaitility.Awaitility.await;

public class OrderFlowTest
{
    private static final String ORDER_API =
            System.getProperty(
                    "order.url",
                    "http://localhost:8082"
            );

    private static final String STORAGE_API =
            System.getProperty(
                    "storage.url",
                    "http://localhost:8080"
            );

    private final RestTemplate rest = new RestTemplate();

    private final KeyCloakTokenService  keyCloakTokenService = new KeyCloakTokenService();

    @Test
    void stockOrderFlow()
    {
        String userToken = keyCloakTokenService.getAccessToken("user1", "user1");

        String managerToken = keyCloakTokenService.getAccessToken("manager1", "manager1");

        // get car in stock
        ResponseEntity<CarResponseDto[]> carsResponse =
                rest.exchange(
                        orderUrl("/api/v1/cars"),
                        HttpMethod.GET,
                        new HttpEntity<>(headers(userToken)),
                        CarResponseDto[].class
                );

        Assertions.assertEquals(HttpStatus.OK, carsResponse.getStatusCode());

        Assertions.assertNotNull(carsResponse.getBody());

        Assertions.assertTrue(carsResponse.getBody().length > 0);

        UUID carId = carsResponse.getBody()[0].getId();

        // create order
        CreateStockOrderRequest request = new CreateStockOrderRequest();

        request.setCarId(carId);

        ResponseEntity<StockCarOrderDto> createResponse =
                rest.exchange(
                        orderUrl("/orders/stock"),
                        HttpMethod.POST,
                        new HttpEntity<>(
                                request,
                                headers(userToken)
                        ),
                        StockCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        StockCarOrderDto order = createResponse.getBody();

        Assertions.assertNotNull(order);

        UUID orderId = order.getOrderId();

        // wait approve
        await()
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() -> {

                ResponseEntity<StockCarOrderDto> response =
                        rest.exchange(
                                orderUrl("/orders/stock/" + orderId),
                                HttpMethod.GET,
                                new HttpEntity<>(headers(userToken)),
                                StockCarOrderDto.class
                        );

                Assertions.assertNotNull(response.getBody());
                Assertions.assertEquals("MANAGER APPROVED", response.getBody().getState());
            });

        // pay
        ResponseEntity<StockCarOrderDto> payResponse =
                rest.exchange(
                        orderUrl("/orders/stock/" + orderId + "/pay"),
                        HttpMethod.POST,
                        new HttpEntity<>(headers(userToken)),
                        StockCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, payResponse.getStatusCode());

        // wait for pickup
        await()
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() -> {

                ResponseEntity<StockCarOrderDto> response =
                        rest.exchange(
                                orderUrl("/orders/stock/" + orderId),
                                HttpMethod.GET,
                                new HttpEntity<>(headers(userToken)),
                                StockCarOrderDto.class
                        );

                Assertions.assertNotNull(response.getBody());
                Assertions.assertEquals("READY FOR PICKUP", response.getBody().getState());
            });

        // finish order

        ResponseEntity<StockCarOrderDto> finishResponse =
                rest.exchange(
                        orderUrl("/orders/stock/" + orderId + "/finish"),
                        HttpMethod.POST,
                        new HttpEntity<>(headers(managerToken)),
                        StockCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, finishResponse.getStatusCode());

        // check order
        ResponseEntity<StockCarOrderDto> orderResponse =
                rest.exchange(
                        orderUrl("/orders/stock/" + orderId),
                        HttpMethod.GET,
                        new HttpEntity<>(headers(userToken)),
                        StockCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
        Assertions.assertNotNull(orderResponse.getBody());
        Assertions.assertEquals("COMPLETED", orderResponse.getBody().getState());
    }

    @Test
    void configuredOrderFlow()
    {
        String userToken = keyCloakTokenService.getAccessToken("user1", "user1");
        String warehouseToken = keyCloakTokenService.getAccessToken("warehouse1", "warehouse1");
        String managerToken = keyCloakTokenService.getAccessToken("manager1", "manager1");

        // get car models
        ResponseEntity<CarConfigurationDto[]> models =
                rest.exchange(
                        storageUrl("/car-models"),
                        HttpMethod.GET,
                        new HttpEntity<>(headers(userToken)),
                        CarConfigurationDto[].class
                );

        Assertions.assertNotNull(models.getBody());
        Assertions.assertTrue(models.getBody().length > 0);

        // add parts in available
        CarConfigurationDto model = models.getBody()[0];
        addStock(model.getBody().getId(), warehouseToken);
        addStock(model.getEngine().getId(), warehouseToken);
        addStock(model.getGearBox().getId(), warehouseToken);
        addStock(model.getInterior().getId(), warehouseToken);
        addStock(model.getWheels().getId(), warehouseToken);

        // create order
        ConfiguredCarRequest req = new ConfiguredCarRequest();

        req.setBrand(model.getBrand());
        req.setModel(model.getModel());
        req.setBody(model.getBody().getId());
        req.setEngine(model.getEngine().getId());
        req.setGearBox(model.getGearBox().getId());
        req.setInterior(model.getInterior().getId());
        req.setWheels(model.getWheels().getId());
        req.setColor(model.getColor());
        req.setDrivetrainType(model.getDrivetrainType());

        CreateConfiguredOrderRequest request = new CreateConfiguredOrderRequest();
        request.setConfiguration(req);

        ResponseEntity<ConfiguredCarOrderDto> create =
                rest.exchange(
                        orderUrl("/orders/configured"),
                        HttpMethod.POST,
                        new HttpEntity<>(request, headers(userToken)),
                        ConfiguredCarOrderDto.class
                );

        Assertions.assertNotNull(create.getBody());
        UUID orderId = create.getBody().getOrderId();

        // wait approve
        await()
            .atMost(Duration.ofSeconds(10))
            .untilAsserted(() -> {

                ResponseEntity<ConfiguredCarOrderDto> response =
                        rest.exchange(
                                orderUrl("/orders/configured/" + orderId),
                                HttpMethod.GET,
                                new HttpEntity<>(headers(userToken)),
                                ConfiguredCarOrderDto.class
                        );

                Assertions.assertNotNull(response.getBody());
                Assertions.assertEquals("WAREHOUSE APPROVED", response.getBody().getState());
            });

        // pay
        ResponseEntity<ConfiguredCarOrderDto> payResponse =
                rest.exchange(
                        orderUrl("/orders/configured/" + orderId + "/pay"),
                        HttpMethod.POST,
                        new HttpEntity<>(headers(userToken)),
                        ConfiguredCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, payResponse.getStatusCode());

        // deliver
        ResponseEntity<ConfiguredCarOrderDto> deliverResponse =
                rest.exchange(
                        orderUrl("/orders/configured/" + orderId + "/deliver"),
                        HttpMethod.POST,
                        new HttpEntity<>(headers(managerToken)),
                        ConfiguredCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, deliverResponse.getStatusCode());

        // wait for pickup
        await()
            .atMost(Duration.ofSeconds(15))
            .untilAsserted(() -> {

                ResponseEntity<ConfiguredCarOrderDto> response =
                        rest.exchange(
                                orderUrl("/orders/configured/" + orderId),
                                HttpMethod.GET,
                                new HttpEntity<>(headers(userToken)),
                                ConfiguredCarOrderDto.class
                        );

                Assertions.assertNotNull(response.getBody());
                Assertions.assertEquals("READY FOR PICKUP", response.getBody().getState());
            });

        // finish
        ResponseEntity<ConfiguredCarOrderDto> finishResponse =
                rest.exchange(
                        orderUrl("/orders/configured/" + orderId + "/finish"),
                        HttpMethod.POST,
                        new HttpEntity<>(headers(managerToken)),
                        ConfiguredCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, finishResponse.getStatusCode());

        // check
        ResponseEntity<ConfiguredCarOrderDto> finalResponse =
                rest.exchange(
                        orderUrl("/orders/configured/" + orderId),
                        HttpMethod.GET,
                        new HttpEntity<>(headers(userToken)),
                        ConfiguredCarOrderDto.class
                );

        Assertions.assertEquals(HttpStatus.OK, finalResponse.getStatusCode());
        Assertions.assertNotNull(finalResponse.getBody());
        Assertions.assertEquals("COMPLETED", finalResponse.getBody().getState());
    }

    private String orderUrl(String path)
    {
        return ORDER_API + path;
    }

    private String storageUrl(String path)
    {
        return STORAGE_API + path;
    }

    private HttpHeaders headers(String token)
    {
        HttpHeaders headers =
                new HttpHeaders();

        headers.setBearerAuth(token);

        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    private void addStock(UUID partId, String token)
    {
        AddStockRequest req = new AddStockRequest();
        req.setQuantity(10);

        rest.exchange(
                storageUrl( "/parts/" + partId + "/stock"),
                HttpMethod.POST,
                new HttpEntity<>(req, headers(token)),
                Void.class
        );
    }
}
