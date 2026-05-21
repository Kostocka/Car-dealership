package peipo.ru.storage.domain.models.filters.parts;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;

@AllArgsConstructor
@Getter
public class ColorFilter implements Filter<CarModel>
{
    private final String color;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return Objects.equals(entity.getColor(), color);
    }
}
