package peipo.ru.cardealership.infrastructure.persistence.mapper.parts;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.vo.EnginePower;
import peipo.ru.cardealership.domain.vo.EngineVolume;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.EngineEntity;

@Mapper(componentModel = "spring")
public interface EngineMapper
{
    default EngineEntity toEntity(Engine engine)
    {
        EngineEntity engineEntity = new EngineEntity();
        engineEntity.setFuelType(engine.getFuelType());
        engineEntity.setPower(engine.getPower().horsePower());
        engineEntity.setPower(engine.getPower().horsePower());
        engineEntity.setId(engine.getId().id());
        return engineEntity;
    }

    default Engine toDomain(EngineEntity engineEntity)
    {
        return new Engine(
                new PartId(engineEntity.getId()),
                engineEntity.getFuelType(),
                new EnginePower(engineEntity.getPower()),
                new EngineVolume(engineEntity.getVolume())
        );
    }
}