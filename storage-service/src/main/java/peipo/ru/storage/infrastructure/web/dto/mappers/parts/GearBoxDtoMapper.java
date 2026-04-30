package peipo.ru.storage.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.parts.GearBox;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.infrastructure.web.dto.parts.GearBoxDto;
import peipo.ru.storage.infrastructure.web.dto.parts.requests.CreateGearBoxRequest;

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