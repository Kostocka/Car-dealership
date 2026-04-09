package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.GearBox;
import peipo.ru.cardealership.domain.repository.PartRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGearBoxUseCase
{
    private PartRepository<GearBox> partRepository;

    public List<GearBox> execute()
    {
        return partRepository.findAll();
    }
}