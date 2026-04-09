package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class GetWheelsUseCase
{
    private PartRepository<Wheels> partRepository;

    public void execute()
    {
        partRepository.findAll();
    }
}