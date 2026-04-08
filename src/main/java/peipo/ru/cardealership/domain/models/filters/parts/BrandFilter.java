package peipo.ru.cardealership.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;

@AllArgsConstructor
@Getter
public class BrandFilter implements Filter<CarModel>
{
    private final String brand;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getBrand().equalsIgnoreCase(brand);
    }
}
