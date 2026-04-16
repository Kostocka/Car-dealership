package peipo.ru.cardealership.application.usecases.parts;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class GetEngineUseCase
{
    private PartRepository<Engine> partRepository;

    public List<Engine> execute()
    {
        return partRepository.findAll();
    }
}