package peipo.ru.cardealership.infrastructure.persistence.mapper.parts;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.BodyEntity;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.BodyJpaRepository;

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
