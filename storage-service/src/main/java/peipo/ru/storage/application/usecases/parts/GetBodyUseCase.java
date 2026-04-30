package peipo.ru.storage.application.usecases.parts;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.parts.Body;
import peipo.ru.storage.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class GetBodyUseCase
{
    private PartRepository<Body> partRepository;

    public List<Body> execute()
    {
        return partRepository.findAll();
    }
}