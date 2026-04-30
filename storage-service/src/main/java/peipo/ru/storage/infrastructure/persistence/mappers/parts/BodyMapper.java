package peipo.ru.storage.infrastructure.persistence.mappers.parts;

import org.mapstruct.Mapper;
import peipo.ru.storage.domain.models.parts.Body;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.BodyEntity;

@Mapper(componentModel = "spring")
public abstract class BodyMapper
{
    public Body toDomain(BodyEntity entity)
    {
        return new Body(
                new PartId(entity.getId()),
                entity.getBodyType()
        );
    }

    public BodyEntity toEntity(Body body)
    {
        BodyEntity bodyEntity = new BodyEntity();
        bodyEntity.setBodyType(body.getType());
        bodyEntity.setId(body.getId().id());
        return bodyEntity;
    }
}
