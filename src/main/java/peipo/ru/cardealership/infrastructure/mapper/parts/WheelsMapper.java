package peipo.ru.cardealership.infrastructure.mapper.parts;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.WheelsEntity;

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
