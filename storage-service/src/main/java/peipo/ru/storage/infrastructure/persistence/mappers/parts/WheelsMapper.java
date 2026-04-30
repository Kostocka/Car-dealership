package peipo.ru.storage.infrastructure.persistence.mappers.parts;

import org.mapstruct.Mapper;
import peipo.ru.storage.domain.models.parts.Wheels;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.WheelsEntity;

@Mapper(componentModel = "spring")
public interface WheelsMapper
{
    default Wheels toDomain(WheelsEntity wheelsEntity)
    {
        return new Wheels(
                new PartId(wheelsEntity.getId()),
                wheelsEntity.getSize()
        );
    }

    default WheelsEntity toEntity(Wheels wheels)
    {
        WheelsEntity wheelsEntity = new WheelsEntity();
        wheelsEntity.setSize(wheels.getSize());
        wheelsEntity.setId(wheels.getId().id());
        return wheelsEntity;
    }
}
