package peipo.ru.cardealership.application.usecases.parts;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.repository.PartRepository;

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