package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.vo.BodyType;

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