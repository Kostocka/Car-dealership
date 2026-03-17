package org.kostocka.cardealership.domain.models.rules;

import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.exception.IncompatibleComponentException;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.repository.ModelPartCompatibilityRepository;
import org.kostocka.cardealership.domain.vo.id.PartId;

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