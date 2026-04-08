package peipo.ru.cardealership.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.GearBox;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.cars.GearBoxDto;

@Component
@RequiredArgsConstructor
public class GearBoxDtoMapper
{
    public GearBox toDomain(GearBoxDto gearBoxDto)
    {
        return new GearBox(
                new PartId(gearBoxDto.getId()),
                gearBoxDto.getGearBoxType()
        );
    }

    public GearBoxDto toDto(GearBox gearBox)
    {
        return new GearBoxDto(
                gearBox.getId().id(),
                gearBox.getGearBoxType()
        );
    }
}