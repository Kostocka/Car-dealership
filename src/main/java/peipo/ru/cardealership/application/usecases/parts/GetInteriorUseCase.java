package peipo.ru.cardealership.application.usecases.parts;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.Interior;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class GetInteriorUseCase
{
    private PartRepository<Interior> partRepository;

    public List<Interior> execute()
    {
        return partRepository.findAll();
    }
}
