package peipo.ru.storage.infrastructure.persistence.mappers.parts;

import org.mapstruct.Mapper;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.parts.Engine;
import peipo.ru.storage.domain.vo.EnginePower;
import peipo.ru.storage.domain.vo.EngineVolume;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.EngineEntity;

@Mapper(componentModel = "spring")
public abstract class EngineMapper
{
    public EngineEntity toEntity(Engine engine)
    {
        EngineEntity engineEntity = new EngineEntity();
        engineEntity.setFuelType(engine.getFuelType());
        engineEntity.setPower(engine.getPower().horsePower());
        engineEntity.setVolume(engine.getVolume().volume());
        engineEntity.setId(engine.getId().id());
        return engineEntity;
    }

    public Engine toDomain(EngineEntity engineEntity)
    {
        return new Engine(
                new PartId(engineEntity.getId()),
                engineEntity.getFuelType(),
                new EnginePower(engineEntity.getPower()),
                new EngineVolume(engineEntity.getVolume())
        );
    }
}