package peipo.ru.cardealership.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;

@AllArgsConstructor
@Getter
public class EngineValueFilter implements Filter<CarModel>
{
    private final int minVolume;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getEngine().getVolume().volume() >= minVolume;
    }
}