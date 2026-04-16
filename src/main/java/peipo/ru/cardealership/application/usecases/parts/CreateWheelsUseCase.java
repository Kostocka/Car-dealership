package peipo.ru.cardealership.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.repository.PartRepository;

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
