package peipo.ru.cardealership.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.vo.FuelType;

@AllArgsConstructor
@Getter
public class FuelTypeFilter implements Filter<CarModel>
{
    private final FuelType type;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getEngine().getFuelType() == this.type;
    }
}

