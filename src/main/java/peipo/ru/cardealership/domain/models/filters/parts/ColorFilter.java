package peipo.ru.cardealership.domain.models.filters.parts;

import java.awt.Color;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;

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
