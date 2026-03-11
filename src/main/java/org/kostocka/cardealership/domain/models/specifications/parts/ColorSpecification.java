package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import java.awt.*;

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
