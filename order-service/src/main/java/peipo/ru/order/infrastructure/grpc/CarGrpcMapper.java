package peipo.ru.order.infrastructure.grpc;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import peipo.ru.common.dto.cars.CarConfigurationDto;
import peipo.ru.common.dto.cars.CarResponseDto;
import peipo.ru.common.dto.parts.*;
import peipo.ru.common.grpc.*;
import peipo.ru.common.vo.BodyType;
import peipo.ru.common.vo.DrivetrainType;
import peipo.ru.common.vo.FuelType;
import peipo.ru.common.vo.GearBoxType;

@Component
public class CarGrpcMapper
{
    public CarResponseDto toDto(CarMessage message)
    {
        var config = message.getConfiguration();

        return new CarResponseDto(
                UUID.fromString(message.getId()),
                new CarConfigurationDto(
                        UUID.fromString(config.getId()),
                        config.getBrand(),
                        config.getModel(),

                        new BodyDto(
                                UUID.fromString(config.getBody().getId()),
                                BodyType.valueOf(config.getBody().getType())
                        ),

                        new EngineDto(
                                UUID.fromString(config.getEngine().getId()),
                                config.getEngine().getVolume(),
                                config.getEngine().getPower(),
                                mapFuelType(config.getEngine().getFuelType())
                        ),

                        new GearBoxDto(
                                UUID.fromString(config.getGearBox().getId()),
                                mapGearBoxType(config.getGearBox().getType())
                        ),

                        new InteriorDto(
                                UUID.fromString(config.getInterior().getId()),
                                config.getInterior().getName()
                        ),

                        new WheelsDto(
                                UUID.fromString(config.getWheels().getId()),
                                config.getWheels().getSize()
                        ),

                        mapDrivetrainType(config.getDrivetrainType()),
                        config.getColor()
                )
        );
    }

    public List<CarResponseDto> toDto(List<CarMessage> messages)
    {
        return messages.stream()
                .map(this::toDto)
                .toList();
    }

    private DrivetrainType mapDrivetrainType(DrivetrainTypeGrpc type)
    {
        return switch (type)
        {
            case FWD -> DrivetrainType.FWD;
            case RWD -> DrivetrainType.RWD;
            case FOURWD -> DrivetrainType.FourWD;
            case AWD -> DrivetrainType.AWD;
            default -> throw new IllegalArgumentException(
                    "Unknown drivetrain type: " + type
            );
        };
    }

    private FuelType mapFuelType(FuelTypeGrpc type)
    {
        return switch (type)
        {
            case GASOLINE -> FuelType.GASOLINE;
            case ELECTRIC -> FuelType.ELECTRIC;
            case DIESEL -> FuelType.DIESEL;
            default -> throw new IllegalArgumentException(
                    "Unknown fuel type: " + type
            );
        };
    }

    private GearBoxType mapGearBoxType(String type)
    {
        return switch (type)
        {
            case "MANUAL" -> GearBoxType.MANUAL;
            case "AUTOMATIC" -> GearBoxType.AUTOMATIC;
            default -> throw new IllegalArgumentException(
                    "Unknown gearbox type: " + type
            );
        };
    }
}