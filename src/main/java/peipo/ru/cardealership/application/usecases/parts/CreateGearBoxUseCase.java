package peipo.ru.cardealership.application.usecases.parts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.parts.GearBox;
import peipo.ru.cardealership.domain.repository.PartRepository;

@Component
@RequiredArgsConstructor
public class CreateGearBoxUseCase
{
    private final PartRepository<GearBox> partRepository;

    public void execute(GearBox part)
    {
        partRepository.save(part);
    }
}