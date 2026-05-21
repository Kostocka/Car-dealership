package peipo.ru.storage.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.dto.parts.BodyDto;
import peipo.ru.common.dto.parts.requests.CreateBodyRequest;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.parts.Body;

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