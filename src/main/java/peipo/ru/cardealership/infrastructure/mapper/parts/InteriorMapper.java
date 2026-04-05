package peipo.ru.cardealership.infrastructure.mapper.parts;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.parts.Interior;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.InteriorEntity;

@Mapper(componentModel = "spring")
public interface InteriorMapper
{
    default Interior toDomain(InteriorEntity interiorEntity)
    {
        return new Interior(
                new PartId(interiorEntity.getPartId()),
                interiorEntity.getMaterial()
        );
    }

    default InteriorEntity toEntity(Interior interior)
    {
        InteriorEntity interiorEntity = new InteriorEntity();
        interiorEntity.setMaterial(interior.getMaterial());
        interiorEntity.setPartId(interior.getId().id());
        return interiorEntity;
    }
}
