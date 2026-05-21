package peipo.ru.storage.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.parts.GearBox;
import peipo.ru.storage.domain.repository.PartRepository;

@Component
@RequiredArgsConstructor
public class CreateGearBoxUseCase
{
    private final PartRepository<GearBox> partRepository;

    public GearBox execute(GearBox part)
    {
        return partRepository.save(part);
    }
}