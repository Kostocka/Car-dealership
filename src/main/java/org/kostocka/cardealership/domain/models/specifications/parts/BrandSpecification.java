package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;

@AllArgsConstructor
public class BrandSpecification implements Specification<CarModel>
{
    private final String brand;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getBrand().equalsIgnoreCase(brand);
    }
}
