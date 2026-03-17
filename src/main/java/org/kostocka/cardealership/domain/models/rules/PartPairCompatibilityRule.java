package org.kostocka.cardealership.domain.models.rules;

import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.exception.IncompatibleComponentException;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.repository.PartCompatibilityRepository;
import org.kostocka.cardealership.domain.vo.id.PartId;

@AllArgsConstructor
public class PartPairCompatibilityRule implements CompatibilityRule
{
    private final PartCompatibilityRepository repository;

    private final Function<CarModel, PartId> firstExtractor;
    private final Function<CarModel, PartId> secondExtractor;

    @Override
    public void validate(CarModel carModel)
    {
        PartId id1 = firstExtractor.apply(carModel);
        PartId id2 = secondExtractor.apply(carModel);

        if (repository.isCompatible(id1, id2))
        {
            return;
        }
        throw new IncompatibleComponentException("Part incompatible " + id1.id() + "with" + id2.id());
    }
}
