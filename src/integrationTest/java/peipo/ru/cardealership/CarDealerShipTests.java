package peipo.ru.cardealership;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;
import peipo.ru.cardealership.domain.vo.BodyType;
import peipo.ru.cardealership.infrastructure.web.dto.MoneyDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarConfigurationDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarResponseDto;
import peipo.ru.cardealership.infrastructure.web.dto.orders.ConfiguredCarOrderDto;
import peipo.ru.cardealership.infrastructure.web.dto.orders.CreateConfiguredOrderRequest;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarDealerShipTests
{
    @Autowired
    private TestRestTemplate testRestTemplate;

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
            assertEquals(1, rs.getInt(1));
        }
    }

    @Test
    void getAllCars()
    {
        ResponseEntity<CarResponseDto[]> response = testRestTemplate.getForEntity("/cars/filter", CarResponseDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length == 1);
    }

    @Test
    void createTestDrive()
    {
        ResponseEntity<CarResponseDto[]> cars = testRestTemplate.getForEntity("/cars/filter",
                CarResponseDto[].class
        );

        UUID carId = cars.getBody()[0].getId();

        System.out.println("carId: " + carId);

        CreateTestDriveRequestDto dto = new CreateTestDriveRequestDto();
        dto.setClientId(UUID.randomUUID());
        dto.setCarId(carId);

        ResponseEntity<TestDriveResponseDto> response = testRestTemplate.postForEntity("/test-drives/requests",
                dto,
                TestDriveResponseDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldCreateBodyAndGetIt()
    {
        CreateBodyRequest request = new CreateBodyRequest();
        request.setBodyType(BodyType.SEDAN);

        testRestTemplate.postForEntity("/parts/bodies", request, Void.class);

        ResponseEntity<BodyDto[]> response =
                testRestTemplate.getForEntity("/parts/bodies", BodyDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void shouldSetAndGetPrice()
    {
        UUID partId = UUID.randomUUID();

        testRestTemplate.postForEntity("/parts/" + partId + "/price",
                new MoneyDto(BigDecimal.valueOf(100)),
                Void.class);

        MoneyDto price = testRestTemplate.getForObject(
                "/parts/" + partId + "/price",
                MoneyDto.class
        );

        assertEquals(100, price.getPartPrice().intValue());
    }

    @Test
    void filterCarsByBrand() {
        ResponseEntity<CarResponseDto[]> response =
                testRestTemplate.getForEntity("/cars/filter?brand=Audi", CarResponseDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createConfiguredOrder()
    {
        ResponseEntity<CarConfigurationDto[]> response =
            testRestTemplate.getForEntity("/car-models", CarConfigurationDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        CarConfigurationDto baseModel = response.getBody()[0];

        addStock(baseModel.getBody().getId(), 10);
        addStock(baseModel.getEngine().getId(), 10);
        addStock(baseModel.getGearBox().getId(), 10);
        addStock(baseModel.getInterior().getId(), 10);
        addStock(baseModel.getWheels().getId(), 10);

        CreateConfiguredOrderRequest request = new CreateConfiguredOrderRequest();
        request.setClientId(UUID.randomUUID());
        request.setConfiguration(baseModel);

        ResponseEntity<ConfiguredCarOrderDto> orderResponse =
                testRestTemplate.postForEntity("/orders/configured", request, ConfiguredCarOrderDto.class);

        assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
        assertNotNull(orderResponse.getBody());

        ConfiguredCarOrderDto order = orderResponse.getBody();
        assertEquals(baseModel.getBrand(), order.getConfiguration().getBrand());
        assertEquals(baseModel.getModel(), order.getConfiguration().getModel());

        ResponseEntity<ConfiguredCarOrderDto[]> listResponse =
                testRestTemplate.getForEntity("/orders/configured", ConfiguredCarOrderDto[].class);

        assertEquals(HttpStatus.OK, listResponse.getStatusCode());
        assertNotNull(listResponse.getBody());
        assertTrue(listResponse.getBody().length > 0);
    }

    private void addStock(UUID partId, int quantity)
    {
        AddStockRequest req = new AddStockRequest();
        req.setQuantity(quantity);

        testRestTemplate.postForEntity("/parts/" + partId + "/stock", req, Void.class);
    }
}
