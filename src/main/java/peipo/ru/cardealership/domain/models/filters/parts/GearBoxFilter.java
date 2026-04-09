package peipo.ru.cardealership.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.vo.GearBoxType;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class GearBoxFilter implements Filter<CarModel>
{
    private final String type;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return Objects.equals(entity.getGearBox().getGearBoxType().toString(), type);
    }
}
