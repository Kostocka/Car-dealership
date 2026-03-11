package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.vo.Money;

@AllArgsConstructor
public class PriceSpecification implements Specification<CarModel>
{
    private final Money maxPrice;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getBasePrice() == maxPrice;
    }
}
