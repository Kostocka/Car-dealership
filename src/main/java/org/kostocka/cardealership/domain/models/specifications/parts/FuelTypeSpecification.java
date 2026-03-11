package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.vo.FuelType;

@AllArgsConstructor
public class FuelTypeSpecification implements Specification<CarModel>
{
    private final FuelType type;
    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getEngine().getFuelType() == this.type;
    }
}

