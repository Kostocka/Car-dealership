package peipo.ru.cardealership.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.Interior;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Component
@RequiredArgsConstructor
public class CreateInteriorUseCase
{
    private final PartRepository<Interior> partRepository;

    public void execute(Interior part)
    {
        partRepository.save(part);
    }
}