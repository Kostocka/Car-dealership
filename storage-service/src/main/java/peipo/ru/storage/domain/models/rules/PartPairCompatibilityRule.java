package peipo.ru.storage.domain.models.rules;

import java.util.function.Function;
import lombok.AllArgsConstructor;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.exception.IncompatibleComponentException;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.PartCompatibilityRepository;

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
