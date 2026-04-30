package peipo.ru.storage.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.parts.Interior;
import peipo.ru.storage.domain.repository.PartRepository;

@Component
@RequiredArgsConstructor
public class CreateInteriorUseCase
{
    private final PartRepository<Interior> partRepository;

    public Interior execute(Interior part)
    {
        return partRepository.save(part);
    }
}