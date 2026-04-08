package peipo.ru.cardealership.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.cars.WheelsDto;

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

    public WheelsDto toDto(Wheels wheels)
    {
        return new WheelsDto(
                wheels.getId().id(),
                wheels.getSize()
        );
    }
}