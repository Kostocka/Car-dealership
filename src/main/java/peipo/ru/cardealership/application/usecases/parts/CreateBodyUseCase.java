package peipo.ru.cardealership.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Component
@RequiredArgsConstructor
public class CreateBodyUseCase
{
    private final PartRepository<Body> partRepository;

    public Body execute(Body part)
    {
        return partRepository.save(part);
    }
}
