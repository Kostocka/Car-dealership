package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.repository.ModelPartCompatibilityRepository;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.PartId;

@AllArgsConstructor
public class AddModelPartCompatibilityUseCase
{
    private ModelPartCompatibilityRepository  modelPartCompatibilityRepository;

    public void execute(PartId partId, CarModelId  carModelId)
    {
        modelPartCompatibilityRepository.add(partId, carModelId);
    }
}
