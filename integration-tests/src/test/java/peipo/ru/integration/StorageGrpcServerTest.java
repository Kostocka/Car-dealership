package peipo.ru.integration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peipo.ru.common.grpc.*;

public class StorageGrpcServerTest
{
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

    private ManagedChannel channel;
    private CarGrpcServiceGrpc.CarGrpcServiceBlockingStub stub;

    @BeforeEach
    void setUp()
    {
        channel = ManagedChannelBuilder
                        .forAddress(STORAGE_GRPC_HOST, STORAGE_GRPC_PORT)
                        .usePlaintext()
                        .build();

        stub = CarGrpcServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    void tearDown()
    {
        channel.shutdownNow();
    }

    @Test
    void shouldGetCars()
    {
        // Act
        var request = GetAllCarsRequest.newBuilder()
                        .setFilter(CarFilterMessage
                                        .newBuilder()
                                        .build()
                        )
                        .build();

        var response = stub.getAllCars(request);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getCarsList().isEmpty());
    }

    @Test
    void shouldGetCarById()
    {
        // Arrange
        var cars = stub.getAllCars(GetAllCarsRequest.newBuilder().build());
        var id = cars.getCars(0).getId();

        // Act
        var response =
                stub.getCarById(
                        GetCarByIdRequest.newBuilder()
                                .setId(id)
                                .build()
                );

        // Assert
        Assertions.assertNotNull(response.getCar());
    }
}
