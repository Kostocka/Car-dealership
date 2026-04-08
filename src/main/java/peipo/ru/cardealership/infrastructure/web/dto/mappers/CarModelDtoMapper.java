package peipo.ru.cardealership.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarConfigurationDto;

import java.awt.*;

@Component
@RequiredArgsConstructor
public class CarModelDtoMapper
{
    private final EngineDtoMapper engineDtoMapper;
    private final BodyDtoMapper bodyDtoMapper;
    private final WheelsDtoMapper wheelsDtoMapper;
    private final GearBoxDtoMapper gearBoxDtoMapper;
    private final InteriorDtoMapper interiorDtoMapper;

    public CarModel toDomain(CarConfigurationDto dto)
    {
        return new CarModel(
                CarModelId.generate(),
                dto.getBrand(),
                dto.getModel(),
                bodyDtoMapper.toDomain(dto.getBody()),
                engineDtoMapper.toDomain(dto.getEngine()),
                gearBoxDtoMapper.toDomain(dto.getGearBox()),
                interiorDtoMapper.toDomain(dto.getInterior()),
                wheelsDtoMapper.toDomain(dto.getWheels()),
                dto.getDrivetrainType(),
                Color.decode(dto.getColor())
        );
    }

    public CarConfigurationDto toDto(CarModel model)
    {
        return new CarConfigurationDto(
                model.getBrand(),
                model.getModel(),
                bodyDtoMapper.toDto(model.getBody()),
                engineDtoMapper.toDto(model.getEngine()),
                gearBoxDtoMapper.toDto(model.getGearBox()),
                interiorDtoMapper.toDto(model.getInterior()),
                wheelsDtoMapper.toDto(model.getWheels()),
                model.getDrivetrainType(),
                model.getColor().toString()
        );
    }
}
