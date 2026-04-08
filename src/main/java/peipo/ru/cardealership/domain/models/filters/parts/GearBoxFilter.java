package peipo.ru.cardealership.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.vo.GearBoxType;

@AllArgsConstructor
@Getter
public class GearBoxFilter implements Filter<CarModel>
{
    private final GearBoxType type;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getGearBox().getGearBoxType() == type;
    }
}
