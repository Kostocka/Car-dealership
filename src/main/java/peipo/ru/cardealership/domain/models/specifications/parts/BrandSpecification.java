package peipo.ru.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;

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
