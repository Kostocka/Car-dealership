package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.Part;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class GetParts<T extends Part>
{
    private PartRepository<T> partRepository;

    public void execute()
    {
        partRepository.findAll();
    }
}
