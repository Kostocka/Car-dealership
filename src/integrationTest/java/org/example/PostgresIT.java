package org.example;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
class PostgresIT {

    @Test
    void canConnectAndQuery() throws Exception {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/cardealership",
                "user",
                "user")) {

            ResultSet rs = connection.createStatement().executeQuery("select 1");
            rs.next();
            assertEquals(1, rs.getInt(1));
        }
    }
}
