package peipo.ru.cardealership.infrastructure.persistence.mapper.parts;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.vo.EnginePower;
import peipo.ru.cardealership.domain.vo.EngineVolume;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.EngineEntity;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.BodyJpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.EngineJpaRepository;

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