package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.parts.Part;
import peipo.ru.cardealership.domain.repository.PartRepository;

@AllArgsConstructor
public class CreatePartUseCase<T extends Part>
{
    private PartRepository<T> partRepository;

    public void execute(T part)
    {
        partRepository.save(part);
    }
}
