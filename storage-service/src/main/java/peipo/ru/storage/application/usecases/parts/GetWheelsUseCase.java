package peipo.ru.storage.application.usecases.parts;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.parts.Wheels;
import peipo.ru.storage.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class GetWheelsUseCase
{
    private PartRepository<Wheels> partRepository;

    public List<Wheels> execute()
    {
        return partRepository.findAll();
    }
}