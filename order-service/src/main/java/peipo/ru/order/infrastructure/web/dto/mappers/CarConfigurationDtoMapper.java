package peipo.ru.order.infrastructure.web.dto.mappers;

import org.springframework.stereotype.Component;
import peipo.ru.common.dto.ConfiguredCarRequest;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.PartId;

@Component
public class CarConfigurationDtoMapper
{
    public CarConfiguration toDomain(ConfiguredCarRequest dto)
    {
        return CarConfiguration.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .body(new PartId(dto.getBody()))
                .engine(new PartId(dto.getEngine()))
                .gearBox(new PartId(dto.getGearBox()))
                .interior(new PartId(dto.getInterior()))
                .wheels(new PartId(dto.getWheels()))
                .drivetrainType(dto.getDrivetrainType())
                .color(dto.getColor())
                .build();
    }

    public ConfiguredCarRequest toDto(CarConfiguration configuration)
    {
        return new ConfiguredCarRequest(
                configuration.getBrand(),
                configuration.getModel(),
                configuration.getBody().id(),
                configuration.getEngine().id(),
                configuration.getGearBox().id(),
                configuration.getInterior().id(),
                configuration.getWheels().id(),
                configuration.getDrivetrainType(),
                configuration.getColor()
        );
    }
}
