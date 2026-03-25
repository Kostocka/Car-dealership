package peipo.ru.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;

@AllArgsConstructor
public class ModelSpecification implements Specification<CarModel>
{
    private final String model;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getModel().equalsIgnoreCase(model);
    }
}
