package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;

@AllArgsConstructor
public class EnginePowerSpecification implements Specification<CarModel>
{
    private final int minPower;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getEngine().getPower().horsePower() >= minPower;
    }
}
