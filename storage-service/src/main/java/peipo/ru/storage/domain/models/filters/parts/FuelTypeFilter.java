package peipo.ru.storage.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.vo.FuelType;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;

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

