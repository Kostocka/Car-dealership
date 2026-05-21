package peipo.ru.storage.domain.models.rules;

import java.util.function.Function;
import lombok.AllArgsConstructor;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.exception.IncompatibleComponentException;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.ModelPartCompatibilityRepository;

@AllArgsConstructor
public class ModelPartCompatibilityRule implements CompatibilityRule
{
    private final ModelPartCompatibilityRepository repository;

    private final Function<CarModel, PartId> extractor;

    @Override
    public void validate(CarModel carModel)
    {
        PartId id = extractor.apply(carModel);

        if (!repository.isCompatible(id, carModel.getModelId()))
        {
            throw new IncompatibleComponentException("Part uncompatible with" + carModel.getModelId());
        }
    }
}