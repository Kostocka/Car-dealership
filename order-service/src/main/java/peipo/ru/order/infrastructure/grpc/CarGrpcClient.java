package peipo.ru.order.infrastructure.grpc;

import io.grpc.StatusRuntimeException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.dto.CarFilterDto;
import peipo.ru.common.grpc.*;

@Service
@RequiredArgsConstructor
public class CarGrpcClient
{
    private static final Logger log = Logger.getLogger(CarGrpcClient.class.getName());

    private final CarGrpcServiceGrpc.CarGrpcServiceBlockingStub stub;
    private final GrpcStorageProperties props;
    private final CarFilterGrpcMapper carFilterGrpcMapper;

    public List<CarMessage> getAllCars(CarFilterDto filter)
    {
        try
        {
            log.info("gRPC -> GetAllCars");

            var request = GetAllCarsRequest.newBuilder()
                    .setFilter(carFilterGrpcMapper.toMessage(filter))
                    .build();

            var response = stub
                    .withDeadlineAfter(props.getTimeoutMs(), TimeUnit.MILLISECONDS)
                    .getAllCars(request);

            return response.getCarsList();

        } catch (StatusRuntimeException e)
        {
            log.severe("gRPC GetAllCars failed: " + e.getMessage());
            throw new RuntimeException("StorageService unavailable");
        }
    }

    public CarMessage getById(String id)
    {
        try
        {
            log.info("gRPC -> GetCarById");

            var request = GetCarByIdRequest.newBuilder()
                    .setId(id)
                    .build();

            return stub
                    .withDeadlineAfter(props.getTimeoutMs(), TimeUnit.MILLISECONDS)
                    .getCarById(request)
                    .getCar();

        } catch (StatusRuntimeException e)
        {
            log.severe("gRPC GetById failed: " + e.getMessage());
            throw new RuntimeException("StorageService unavailable");
        }
    }
}
