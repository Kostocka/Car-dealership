package peipo.ru.storage.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.parts.Engine;
import peipo.ru.storage.domain.repository.PartRepository;

@Component
@RequiredArgsConstructor
public class CreateEngineUseCase
{
    private final PartRepository<Engine> partRepository;

    public Engine execute(Engine part)
    {
        return partRepository.save(part);
    }
}