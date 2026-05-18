package peipo.ru.storage.infrastructure.grpc;

import io.grpc.stub.StreamObserver;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.grpc.*;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.storage.application.usecases.models.GetCarByIdUseCase;
import peipo.ru.storage.application.usecases.models.GetCarsUseCase;
import peipo.ru.storage.infrastructure.web.dto.mappers.cars.CarFilterMapper;

@Component
@RequiredArgsConstructor
public class CarGrpcServiceImpl extends CarGrpcServiceGrpc.CarGrpcServiceImplBase
{
    private final GetCarByIdUseCase getCarByIdUseCase;
    private final GetCarsUseCase getCarsUseCase;
    private final CarGrpcMapper carGrpcMapper;
    private final CarFilterMapper carFilterMapper;
    private final CarFilterGrpcMapper carFilterGrpcMapper;

    @Override
    public void getCarById(GetCarByIdRequest request, StreamObserver<GetCarByIdResponse> responseObserver)
    {
        var car = getCarByIdUseCase.execute(new CarId(UUID.fromString(request.getId())));
        var response = GetCarByIdResponse.newBuilder()
                .setCar(carGrpcMapper.toMessage(car))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllCars(GetAllCarsRequest request, StreamObserver<GetAllCarsResponse> responseObserver)
    {
        var filterDto = carFilterGrpcMapper.toDto(request.getFilter());
        var filter = carFilterMapper.toDomain(filterDto);
        var cars = getCarsUseCase.execute(filter);
        var response = GetAllCarsResponse.newBuilder()
                .addAllCars(carGrpcMapper.toMessage(cars))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
