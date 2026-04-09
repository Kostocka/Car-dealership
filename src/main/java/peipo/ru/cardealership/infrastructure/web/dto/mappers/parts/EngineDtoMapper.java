package peipo.ru.cardealership.infrastructure.web.dto.mappers.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.vo.EnginePower;
import peipo.ru.cardealership.domain.vo.EngineVolume;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.parts.EngineDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.CreateEngineRequest;

@Component
@RequiredArgsConstructor
public class EngineDtoMapper
{
    public Engine toDomain(EngineDto engineDto)
    {
        return new Engine(
                new PartId(engineDto.getId()),
                engineDto.getFuelType(),
                new EnginePower(engineDto.getPower()),
                new EngineVolume(engineDto.getVolume())
        );
    }

    public Engine fromRequest(CreateEngineRequest createEngineRequest)
    {
        return new Engine(
                PartId.generate(),
                createEngineRequest.getFuelType(),
                new EnginePower(createEngineRequest.getPower()),
                new EngineVolume(createEngineRequest.getVolume())
        );
    }

    public EngineDto toDto(Engine engine)
    {
        return new EngineDto(
                engine.getId().id(),
                engine.getVolume().volume(),
                engine.getPower().horsePower(),
                engine.getFuelType()
                );
    }
}
