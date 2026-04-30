package peipo.ru.storage.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.parts.Interior;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.infrastructure.web.dto.parts.InteriorDto;
import peipo.ru.storage.infrastructure.web.dto.parts.requests.CreateInteriorRequest;

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

    public Interior fromRequest(CreateInteriorRequest createInteriorRequest)
    {
        return new Interior(
                PartId.generate(),
                createInteriorRequest.getMaterial()
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