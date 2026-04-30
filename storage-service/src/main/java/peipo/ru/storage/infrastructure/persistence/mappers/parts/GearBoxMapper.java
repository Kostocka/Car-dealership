package peipo.ru.storage.infrastructure.persistence.mappers.parts;

import org.mapstruct.Mapper;
import peipo.ru.storage.domain.models.parts.GearBox;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.GearBoxEntity;

@Mapper(componentModel = "spring", uses = IdMapper.class)
public interface GearBoxMapper
{
    default GearBox toDomain(GearBoxEntity entity)
    {
        return new GearBox(
                new PartId(entity.getId()),
                entity.getGearBoxType()
        );
    }

    default GearBoxEntity toEntity(GearBox gearBox)
    {
        GearBoxEntity gearBoxEntity = new GearBoxEntity();
        gearBoxEntity.setGearBoxType(gearBox.getGearBoxType());
        gearBoxEntity.setId(gearBox.getId().id());
        return gearBoxEntity;
    }
}