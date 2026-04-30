package peipo.ru.storage.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;

@AllArgsConstructor
@Getter
public class ModelFilter implements Filter<CarModel>
{
    private final String model;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getModel().equalsIgnoreCase(model);
    }
}
