package peipo.ru.storage.application.usecases.parts;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.parts.Interior;
import peipo.ru.storage.domain.repository.PartRepository;

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
