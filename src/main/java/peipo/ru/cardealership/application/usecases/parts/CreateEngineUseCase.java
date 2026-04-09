package peipo.ru.cardealership.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.repository.PartRepository;

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