package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.Part;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class CreatePartUseCase<T extends Part>
{
    private PartRepository<T> partRepository;

    public void execute(T part)
    {
        partRepository.save(part);
    }
}
