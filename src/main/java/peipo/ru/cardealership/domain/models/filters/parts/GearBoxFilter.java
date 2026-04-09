package peipo.ru.cardealership.domain.models.filters.parts;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;

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
