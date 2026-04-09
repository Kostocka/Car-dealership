package peipo.ru.cardealership;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;
import peipo.ru.cardealership.domain.vo.BodyType;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarResponseDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.BodyDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.CreateBodyRequest;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.CreateTestDriveRequestDto;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.TestDriveResponseDto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
    void createBody() {
        CreateBodyRequest request = new CreateBodyRequest();
        request.setBodyType(BodyType.CUV);

        ResponseEntity<BodyDto> response =
                testRestTemplate.postForEntity("/parts/bodies", request, BodyDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void filterCarsByBrand() {
        ResponseEntity<CarResponseDto[]> response =
                testRestTemplate.getForEntity("/cars/filter?brand=Audi", CarResponseDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void schemaIsValid() throws Exception {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/cardealership",
                "user",
                "user")) {

            DatabaseMetaData meta = conn.getMetaData();

            asserTableExists(meta, "car");
            asserTableExists(meta, "car_model");
            asserTableExists(meta, "engine");
            asserTableExists(meta, "body");
            asserTableExists(meta, "wheels");
            asserTableExists(meta, "gearbox");
            asserTableExists(meta, "interior");
        }
    }

    private void asserTableExists(DatabaseMetaData meta, String tableName) throws Exception
    {
        ResultSet rs = meta.getTables(null, null, tableName, new String[]{"TABLE"});
        assertTrue(rs.next());
    }
}
