package peipo.ru.cardealership.infrastructure.persistence.mapper.parts;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.BodyEntity;

@Mapper(componentModel = "spring")
public interface BodyMapper
{
    default Body toDomain(BodyEntity entity)
    {
        return new Body(
                new PartId(entity.getId()),
                entity.getBodyType()
        );
    }

    default BodyEntity toEntity(Body body)
    {
        BodyEntity bodyEntity = new BodyEntity();
        bodyEntity.setBodyType(body.getType());
        bodyEntity.setId(body.getId().id());
        return bodyEntity;
    }
}
