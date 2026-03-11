package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;

@AllArgsConstructor
public class EngineValueSpecification implements Specification<CarModel>
{
    private final int minVolume;
    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getEngine().getVolume().volume() >= minVolume;
    }
}