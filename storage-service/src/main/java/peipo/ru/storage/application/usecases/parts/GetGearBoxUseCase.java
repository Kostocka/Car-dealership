package peipo.ru.storage.application.usecases.parts;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.parts.GearBox;
import peipo.ru.storage.domain.repository.PartRepository;

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