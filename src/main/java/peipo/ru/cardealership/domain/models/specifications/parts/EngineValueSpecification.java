package peipo.ru.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;

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