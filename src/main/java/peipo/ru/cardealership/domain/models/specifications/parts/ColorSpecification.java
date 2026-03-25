package peipo.ru.cardealership.domain.models.specifications.parts;

import java.awt.Color;
import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;

@AllArgsConstructor
public class ColorSpecification implements Specification<CarModel>
{
    private final Color color;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getColor() == color;
    }
}
