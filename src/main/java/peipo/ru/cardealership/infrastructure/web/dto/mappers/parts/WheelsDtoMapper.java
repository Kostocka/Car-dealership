package peipo.ru.cardealership.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.parts.WheelsDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.CreateWheelsRequest;

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