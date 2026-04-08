package peipo.ru.cardealership.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Interior;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.cars.InteriorDto;

@Component
@RequiredArgsConstructor
public class InteriorDtoMapper
{
    public Interior toDomain(InteriorDto interiorDto)
    {
        return new Interior(
                new PartId(interiorDto.getId()),
                interiorDto.getMaterial()
        );
    }

    public InteriorDto toDto(Interior interior)
    {
        return new InteriorDto(
                interior.getId().id(),
                interior.getMaterial()
        );
    }
}