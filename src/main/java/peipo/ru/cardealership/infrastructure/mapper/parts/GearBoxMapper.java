package peipo.ru.cardealership.infrastructure.mapper.parts;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.parts.GearBox;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.GearBoxEntity;

@Mapper(componentModel = "spring", uses = IdMapper.class)
public interface GearBoxMapper
{
    default GearBox toDomain(GearBoxEntity entity)
    {
        return new GearBox(
                new PartId(entity.getPartId()),
                entity.getGearBoxType()
        );
    }

    default GearBoxEntity toEntity(GearBox gearBox)
    {
        GearBoxEntity gearBoxEntity = new GearBoxEntity();
        gearBoxEntity.setGearBoxType(gearBox.getGearBoxType());
        gearBoxEntity.setPartId(gearBox.getId().id());
        return gearBoxEntity;
    }
}
