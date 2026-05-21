package peipo.ru.storage.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;

@AllArgsConstructor
@Getter
public class EnginePowerFilter implements Filter<CarModel>
{
    private final int minPower;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getEngine().getPower().horsePower() >= minPower;
    }
}
