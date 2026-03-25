package peipo.ru.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;
import peipo.ru.cardealership.domain.vo.BodyType;

@AllArgsConstructor
public class BodySpecification implements Specification<CarModel>
{
    private final BodyType type;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getBody().getType() == this.type;
    }
}