package peipo.ru.storage.infrastructure.web.dto.mappers.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.dto.cars.CarConfigurationDto;
import peipo.ru.common.dto.cars.CarModelResponceDto;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.vo.CarModelId;
import peipo.ru.storage.infrastructure.web.dto.mappers.parts.*;

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
                new CarModelId(dto.getId()),
                dto.getBrand(),
                dto.getModel(),
                bodyDtoMapper.toDomain(dto.getBody()),
                engineDtoMapper.toDomain(dto.getEngine()),
                gearBoxDtoMapper.toDomain(dto.getGearBox()),
                interiorDtoMapper.toDomain(dto.getInterior()),
                wheelsDtoMapper.toDomain(dto.getWheels()),
                dto.getDrivetrainType(),
                dto.getColor()
        );
    }

    public CarConfigurationDto toDto(CarModel model)
    {
        return new CarConfigurationDto(
                model.getModelId().id(),
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

    public CarModelResponceDto toModelDto(CarModel model)
    {
        return new CarModelResponceDto(
                model.getModelId().id(),
                toDto(model)
        );
    }

}
