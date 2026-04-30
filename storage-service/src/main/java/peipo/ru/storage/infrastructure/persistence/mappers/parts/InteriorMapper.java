package peipo.ru.storage.infrastructure.persistence.mappers.parts;

import org.mapstruct.Mapper;
import peipo.ru.storage.domain.models.parts.Interior;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.InteriorEntity;

@Mapper(componentModel = "spring")
public interface InteriorMapper
{
    default Interior toDomain(InteriorEntity interiorEntity)
    {
        return new Interior(
                new PartId(interiorEntity.getId()),
                interiorEntity.getMaterial()
        );
    }

    default InteriorEntity toEntity(Interior interior)
    {
        InteriorEntity interiorEntity = new InteriorEntity();
        interiorEntity.setMaterial(interior.getMaterial());
        interiorEntity.setId(interior.getId().id());
        return interiorEntity;
    }
}
