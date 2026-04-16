package peipo.ru.cardealership.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.GearBox;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.parts.GearBoxDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.CreateGearBoxRequest;

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

    public GearBox fromRequest(CreateGearBoxRequest createBodyRequest)
    {
        return new GearBox(
                PartId.generate(),
                createBodyRequest.getGearBoxType()
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