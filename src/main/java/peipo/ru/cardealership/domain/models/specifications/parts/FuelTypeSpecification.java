package peipo.ru.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;
import peipo.ru.cardealership.domain.vo.FuelType;

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

