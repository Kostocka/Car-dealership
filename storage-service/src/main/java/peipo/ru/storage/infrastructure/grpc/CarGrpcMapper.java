package peipo.ru.storage.infrastructure.grpc;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.vo.DrivetrainType;
import peipo.ru.common.vo.FuelType;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.grpc.*;

@Component
@RequiredArgsConstructor
public class CarGrpcMapper
{
    public CarMessage toMessage(Car car)
    {
        CarModel model = car.getConfiguration();

        return CarMessage.newBuilder()
                .setId(car.getCarId().toString())
                .setConfiguration(
                        CarConfigurationMessage.newBuilder()
                                .setBrand(model.getBrand())
                                .setModel(model.getModel())
                                .setBody(BodyMessage.newBuilder().setType(model.getBody().getType().name()).build())
                                .setEngine(EngineMessage.newBuilder()
                                        .setPower(model.getEngine().getPower().horsePower())
                                        .setVolume(model.getEngine().getVolume().volume())
                                        .setFuelType(mapFuelType(model.getEngine().getFuelType()))
                                        .build())
                                .setGearBox(GearBoxMessage.newBuilder().setType(model.getGearBox().getGearBoxType().name()).build())
                                .setInterior(InteriorMessage.newBuilder().setName(model.getInterior().getMaterial()).build())
                                .setWheels(WheelsMessage.newBuilder().setSize(model.getWheels().getSize()).build())
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

    private peipo.ru.storage.grpc.DrivetrainType mapDrivetrain(DrivetrainType type)
    {
        return switch (type)
        {
            case FWD -> peipo.ru.storage.grpc.DrivetrainType.FWD;
            case RWD -> peipo.ru.storage.grpc.DrivetrainType.RWD;
            case FourWD -> peipo.ru.storage.grpc.DrivetrainType.FOURWD;
            case AWD -> peipo.ru.storage.grpc.DrivetrainType.AWD;
        };
    }

    private peipo.ru.storage.grpc.FuelType mapFuelType(FuelType fuelType)
    {
        return switch (fuelType)
        {
            case GASOLINE -> peipo.ru.storage.grpc.FuelType.GASOLINE;
            case BIODIESEL -> null;
            case ETHANOL -> null;
            case HYDROGEN -> null;
            case ELECTRIC -> peipo.ru.storage.grpc.FuelType.ELECTRIC;
            case DIESEL -> peipo.ru.storage.grpc.FuelType.DIESEL;
        };
    }
}