package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.parts.GearBox;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Service
@AllArgsConstructor
public class GetGearBoxUseCase
{
    private PartRepository<GearBox> partRepository;

    public void execute()
    {
        partRepository.findAll();
    }
}