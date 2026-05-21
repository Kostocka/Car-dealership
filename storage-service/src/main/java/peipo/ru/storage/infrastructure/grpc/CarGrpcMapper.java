package peipo.ru.storage.infrastructure.grpc;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.grpc.*;
import peipo.ru.common.vo.DrivetrainType;
import peipo.ru.common.vo.FuelType;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.models.CarModel;

@Component
@RequiredArgsConstructor
public class CarGrpcMapper
{
    public CarMessage toMessage(Car car)
    {
        CarModel model = car.getConfiguration();

        return CarMessage.newBuilder()
                .setId(car.getCarId().id().toString())
                .setConfiguration(
                        CarConfigurationMessage.newBuilder()
                                .setId(model.getModelId().id().toString())
                                .setBrand(model.getBrand())
                                .setModel(model.getModel())
                                .setBody(BodyMessage.newBuilder()
                                        .setId(model.getBody().getId().id().toString())
                                        .setType(model.getBody().getType().name()).build())
                                .setEngine(EngineMessage.newBuilder()
                                        .setId(model.getEngine().getId().id().toString())
                                        .setPower(model.getEngine().getPower().horsePower())
                                        .setVolume(model.getEngine().getVolume().volume())
                                        .setFuelType(mapFuelType(model.getEngine().getFuelType()))
                                        .build())
                                .setGearBox(GearBoxMessage.newBuilder()
                                        .setId(model.getGearBox().getId().id().toString())
                                        .setType(model.getGearBox().getGearBoxType().name()).build())
                                .setInterior(InteriorMessage.newBuilder()
                                        .setId(model.getInterior().getId().id().toString())
                                        .setName(model.getInterior().getMaterial()).build())
                                .setWheels(WheelsMessage.newBuilder()
                                        .setId(model.getWheels().getId().id().toString())
                                        .setSize(model.getWheels().getSize()).build())
                                .setDrivetrainType(mapDrivetrain(model.getDrivetrainType()))
                                .setColor(model.getColor())
                                .build()
                )
                .build();
    }

    public List<CarMessage> toMessage(List<Car> cars)
    {
        return cars.stream().map(this::toMessage).toList();
    }

    private DrivetrainTypeGrpc mapDrivetrain(DrivetrainType type)
    {
        return switch (type)
        {
            case FWD -> DrivetrainTypeGrpc.FWD;
            case RWD -> DrivetrainTypeGrpc.RWD;
            case FourWD -> DrivetrainTypeGrpc.FOURWD;
            case AWD -> DrivetrainTypeGrpc.AWD;
        };
    }

    private FuelTypeGrpc mapFuelType(FuelType fuelType)
    {
        return switch (fuelType)
        {
            case GASOLINE -> FuelTypeGrpc.GASOLINE;
            case BIODIESEL -> FuelTypeGrpc.BIODIESEL;
            case ETHANOL -> FuelTypeGrpc.ETHANOL;
            case HYDROGEN -> FuelTypeGrpc.HYDROGEN;
            case ELECTRIC -> FuelTypeGrpc.ELECTRIC;
            case DIESEL -> FuelTypeGrpc.DIESEL;
        };
    }
}