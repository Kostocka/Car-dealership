package peipo.ru.storage.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.parts.Wheels;
import peipo.ru.storage.domain.repository.PartRepository;

@Component
@RequiredArgsConstructor
public class CreateWheelsUseCase
{
    private final PartRepository<Wheels> partRepository;

    public Wheels execute(Wheels part)
    {
        return partRepository.save(part);
    }
}
