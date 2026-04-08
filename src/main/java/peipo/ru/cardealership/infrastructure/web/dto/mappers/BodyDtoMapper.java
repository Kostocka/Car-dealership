package peipo.ru.cardealership.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.cars.BodyDto;

@Component
@RequiredArgsConstructor
public class BodyDtoMapper
{
    public Body toDomain(BodyDto bodyDto)
    {
        return new Body(
                new PartId(bodyDto.getId()),
                bodyDto.getBodyType()
        );
    }

    public BodyDto toDto(Body body)
    {
        return new BodyDto(
                body.getId().id(),
                body.getType()
        );
    }
}