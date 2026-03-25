package peipo.ru.cardealership.domain.models.specifications.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;
import peipo.ru.cardealership.domain.vo.GearBoxType;

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
