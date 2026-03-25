package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.repository.PartCompatibilityRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;

@AllArgsConstructor
public class AddPartCompatibilityUseCase
{
    private PartCompatibilityRepository partCompatibilityRepository;

    public void execute(PartId firstPartId, PartId secondPartId)
    {
        partCompatibilityRepository.addCompatibility(firstPartId, secondPartId);
    }
}
