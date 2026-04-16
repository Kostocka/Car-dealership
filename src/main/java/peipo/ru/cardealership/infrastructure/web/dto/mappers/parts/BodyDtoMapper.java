package peipo.ru.cardealership.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.parts.BodyDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.CreateBodyRequest;

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

    public Body fromRequest(CreateBodyRequest createBodyRequest)
    {
        return new Body(
                PartId.generate(),
                createBodyRequest.getBodyType()
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