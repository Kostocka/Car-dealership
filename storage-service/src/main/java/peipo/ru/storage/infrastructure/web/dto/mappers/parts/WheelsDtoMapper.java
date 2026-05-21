package peipo.ru.storage.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.dto.parts.WheelsDto;
import peipo.ru.common.dto.parts.requests.CreateWheelsRequest;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.parts.Wheels;

@Component
@RequiredArgsConstructor
public class WheelsDtoMapper
{
    public Wheels toDomain(WheelsDto wheelsDto)
    {
        return new Wheels(
                new PartId(wheelsDto.getId()),
                wheelsDto.getSize()
        );
    }

    public Wheels fromRequest(CreateWheelsRequest createBodyRequest)
    {
        return new Wheels(
                PartId.generate(),
                createBodyRequest.getSize()
        );
    }

    public WheelsDto toDto(Wheels wheels)
    {
        return new WheelsDto(
                wheels.getId().id(),
                wheels.getSize()
        );
    }
}