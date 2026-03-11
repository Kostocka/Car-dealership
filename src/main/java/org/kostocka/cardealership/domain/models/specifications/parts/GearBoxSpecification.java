package org.kostocka.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.vo.GearBoxType;

@AllArgsConstructor
public class GearBoxSpecification implements Specification<CarModel>
{
    private final GearBoxType type;
    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getGearBox().getGearBoxType() == type;
    }
}
