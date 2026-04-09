package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.repository.PartRepository;

import java.util.List;

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