package peipo.ru.cardealership;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarResponseDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarDealerShipTests
{
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void setUpDatabase()
    {
        databaseSeeder.seed();
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
}
