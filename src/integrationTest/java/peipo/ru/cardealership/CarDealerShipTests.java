package peipo.ru.cardealership;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.junit.jupiter.Testcontainers;
import peipo.ru.cardealership.domain.vo.BodyType;
import peipo.ru.cardealership.infrastructure.security.KeycloakTokenService;
import peipo.ru.cardealership.infrastructure.web.dto.MoneyDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarConfigurationDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarResponseDto;
import peipo.ru.cardealership.infrastructure.web.dto.orders.ConfiguredCarOrderDto;
import peipo.ru.cardealership.infrastructure.web.dto.orders.CreateConfiguredOrderRequest;
import peipo.ru.cardealership.infrastructure.web.dto.orders.CreateStockOrderRequest;
import peipo.ru.cardealership.infrastructure.web.dto.orders.StockCarOrderDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.BodyDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.AddStockRequest;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.CreateBodyRequest;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.CreateTestDriveRequestDto;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.TestDriveResponseDto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarDealerShipTests
{
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private KeycloakTokenService keycloakTokenService;

    private HttpHeaders headersWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private <T> ResponseEntity<T> getWithToken(String url, String token, Class<T> tClass) {
        return testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headersWithToken(token)), tClass);
    }

    private <T> ResponseEntity<T> postWithToken(String url, Object body, String token, Class<T> tClass) {
        return testRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headersWithToken(token)), tClass);
    }

    private void addStock(UUID partId, int quantity, String adminToken)
    {
        AddStockRequest req = new AddStockRequest();
        req.setQuantity(quantity);

        postWithToken("/parts/" + partId + "/stock", req, adminToken, Void.class);
    }

    @Test
    void canConnectAndQuery() throws Exception
    {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/cardealership",
                "user",
                "user"))
        {

            ResultSet rs = connection.createStatement().executeQuery("select 1");
            rs.next();
            Assertions.assertEquals(1, rs.getInt(1));
        }
    }

    @Test
    void getAllCars()
    {
        String adminToken = keycloakTokenService.getAccessToken("admin1", "admin1");
        ResponseEntity<CarResponseDto[]> response = getWithToken("/cars/filter", adminToken, CarResponseDto[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().length);
    }

    @Test
    void createTestDrive()
    {
        String adminToken = keycloakTokenService.getAccessToken("admin1", "admin1");

        ResponseEntity<CarResponseDto[]> cars = getWithToken("/cars/filter", adminToken, CarResponseDto[].class);

        Assertions.assertNotNull(cars.getBody());
        UUID carId = cars.getBody()[0].getId();

        System.out.println("carId: " + carId);

        CreateTestDriveRequestDto dto = new CreateTestDriveRequestDto();
        dto.setCarId(carId);

        ResponseEntity<TestDriveResponseDto> response = postWithToken("/test-drives/requests", dto, adminToken, TestDriveResponseDto.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCreateBodyAndGetIt()
    {
        String adminToken = keycloakTokenService.getAccessToken("admin1", "admin1");

        CreateBodyRequest request = new CreateBodyRequest();
        request.setBodyType(BodyType.SEDAN);

        postWithToken("/parts/bodies", request, adminToken, Void.class);

        ResponseEntity<BodyDto[]> response =
                getWithToken("/parts/bodies", adminToken, BodyDto[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().length > 0);
    }

    @Test
    void shouldSetAndGetPrice()
    {
        String adminToken = keycloakTokenService.getAccessToken("admin1", "admin1");
        UUID partId = UUID.randomUUID();

        postWithToken("/parts/" + partId + "/price", new MoneyDto(BigDecimal.valueOf(100)), adminToken, Void.class);

        ResponseEntity<MoneyDto> price = getWithToken("/parts/" + partId + "/price", adminToken, MoneyDto.class);

        Assertions.assertNotNull(price.getBody());
        Assertions.assertEquals(100, price.getBody().getPartPrice().intValue());
    }

    @Test
    void filterCarsByBrand() {
        String adminToken = keycloakTokenService.getAccessToken("admin1", "admin1");
        ResponseEntity<CarResponseDto[]> response = getWithToken("/cars/filter?brand=Audi", adminToken, CarResponseDto[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createConfiguredOrder()
    {
        // Arrange
        String userToken = keycloakTokenService.getAccessToken("user1", "user1");
        String user2Token = keycloakTokenService.getAccessToken("user2", "user2");
        String warehouseToken = keycloakTokenService.getAccessToken("warehouse1", "warehouse1");
        String managerToken = keycloakTokenService.getAccessToken("manager1", "manager1");

        ResponseEntity<CarConfigurationDto[]> response = getWithToken("/car-models", userToken, CarConfigurationDto[].class);
        Assertions.assertNotNull(response.getBody());
        CarConfigurationDto baseModel = response.getBody()[0];

        addStock(baseModel.getBody().getId(), 10, warehouseToken);
        addStock(baseModel.getEngine().getId(), 10, warehouseToken);
        addStock(baseModel.getGearBox().getId(), 10, warehouseToken);
        addStock(baseModel.getInterior().getId(), 10, warehouseToken);
        addStock(baseModel.getWheels().getId(), 10, warehouseToken);

        CreateConfiguredOrderRequest request = new CreateConfiguredOrderRequest();
        request.setConfiguration(baseModel);

        // Act create order
        ResponseEntity<ConfiguredCarOrderDto> orderResponse = postWithToken(
                "/orders/configured", request, userToken, ConfiguredCarOrderDto.class);

        // Assert check create
        Assertions.assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
        Assertions.assertNotNull(orderResponse.getBody());
        ConfiguredCarOrderDto order = orderResponse.getBody();
        Assertions.assertEquals(baseModel.getBrand(), order.getConfiguration().getBrand());
        Assertions.assertEquals(baseModel.getModel(), order.getConfiguration().getModel());

        // check user2 does not see user1's order
        ResponseEntity<ConfiguredCarOrderDto[]> listResponseUser2 = getWithToken(
                "/orders/configured", user2Token, ConfiguredCarOrderDto[].class);

        Assertions.assertNotNull(listResponseUser2.getBody());
        Assertions.assertEquals(0, listResponseUser2.getBody().length);

        // Act warehouse approve
        ResponseEntity<ConfiguredCarOrderDto> approveResponse = postWithToken(
                "/orders/configured/" + order.getOrderId() + "/approve", null, warehouseToken, ConfiguredCarOrderDto.class);

        Assertions.assertEquals(HttpStatus.OK, approveResponse.getStatusCode());

        // Act user pay
        ResponseEntity<ConfiguredCarOrderDto> payResponse = postWithToken(
                "/orders/configured/" + order.getOrderId() + "/pay", null, userToken, ConfiguredCarOrderDto.class);

        Assertions.assertEquals(HttpStatus.OK, payResponse.getStatusCode());

        // Act warehouse delivered
        ResponseEntity<ConfiguredCarOrderDto> deliverResponse = postWithToken(
                "/orders/configured/" + order.getOrderId() + "/deliver", null, warehouseToken, ConfiguredCarOrderDto.class);

        Assertions.assertEquals(HttpStatus.OK, deliverResponse.getStatusCode());

        // Act take order
        ResponseEntity<ConfiguredCarOrderDto> finishResponse = postWithToken(
                "/orders/configured/" + order.getOrderId() + "/finish", null, managerToken, ConfiguredCarOrderDto.class);

        Assertions.assertEquals(HttpStatus.OK, finishResponse.getStatusCode());

        // Check users orders
        ResponseEntity<ConfiguredCarOrderDto[]> finalList = getWithToken("/orders/configured", userToken, ConfiguredCarOrderDto[].class);

        // Assert
        Assertions.assertNotNull(finalList.getBody());
        Assertions.assertEquals(1, finalList.getBody().length);
        Assertions.assertEquals(order.getOrderId(), finalList.getBody()[0].getOrderId());
        Assertions.assertEquals(order.getConfiguration().getModel(), finalList.getBody()[0].getConfiguration().getModel());
    }

    @Test
    void createStockOrder() {
        // Arrange
        String userToken = keycloakTokenService.getAccessToken("user1", "user1");
        String user2Token = keycloakTokenService.getAccessToken("user2", "user2");
        String managerToken = keycloakTokenService.getAccessToken("manager1", "manager1");

        ResponseEntity<CarResponseDto[]> carsResponse = getWithToken("/cars/filter", userToken, CarResponseDto[].class);
        Assertions.assertNotNull(carsResponse.getBody());
        Assertions.assertTrue(carsResponse.getBody().length > 0);

        UUID carId = carsResponse.getBody()[0].getId();

        CreateStockOrderRequest createRequest = new CreateStockOrderRequest();
        createRequest.setCarId(carId);

        // Act create order
        ResponseEntity<StockCarOrderDto> createResponse = postWithToken(
                "/orders/stock", createRequest, userToken, StockCarOrderDto.class);

        // Assert check create
        Assertions.assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Assertions.assertNotNull(createResponse.getBody());
        StockCarOrderDto order = createResponse.getBody();
        Assertions.assertEquals(carId, order.getCarId());

        // Act check user2 does not see user1's order
        ResponseEntity<StockCarOrderDto[]> listResponseUser2 = getWithToken(
                "/orders/stock", user2Token, StockCarOrderDto[].class);

        Assertions.assertNotNull(listResponseUser2.getBody());
        Assertions.assertEquals(0, listResponseUser2.getBody().length);

        // Act manager approve
        ResponseEntity<StockCarOrderDto> approveResponse = postWithToken(
                "/orders/stock/" + order.getOrderId() + "/approve", null, managerToken, StockCarOrderDto.class);

        Assertions.assertEquals(HttpStatus.OK, approveResponse.getStatusCode());

        // Act user pay
        ResponseEntity<StockCarOrderDto> payResponse = postWithToken(
                "/orders/stock/" + order.getOrderId() + "/pay", null, userToken, StockCarOrderDto.class);

        Assertions.assertEquals(HttpStatus.OK, payResponse.getStatusCode());

        // Act manager finish
        ResponseEntity<StockCarOrderDto> finishResponse = postWithToken(
                "/orders/stock/" + order.getOrderId() + "/finish", null, managerToken, StockCarOrderDto.class);

        Assertions.assertEquals(HttpStatus.OK, finishResponse.getStatusCode());

        // Act check users orders
        ResponseEntity<StockCarOrderDto[]> finalList = getWithToken("/orders/stock", userToken, StockCarOrderDto[].class);

        // Assert check final list
        Assertions.assertNotNull(finalList.getBody());
        Assertions.assertEquals(1, finalList.getBody().length);
        Assertions.assertEquals(order.getOrderId(), finalList.getBody()[0].getOrderId());
        Assertions.assertEquals(order.getCarId(), finalList.getBody()[0].getCarId());
    }
}