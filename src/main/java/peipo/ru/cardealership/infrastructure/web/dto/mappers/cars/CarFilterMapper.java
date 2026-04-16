package peipo.ru.cardealership.infrastructure.web.dto.mappers.cars;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.AndFilter;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.models.filters.parts.BrandFilter;
import peipo.ru.cardealership.domain.models.filters.parts.EnginePowerFilter;
import peipo.ru.cardealership.domain.models.filters.parts.GearBoxFilter;
import peipo.ru.cardealership.domain.models.filters.parts.ModelFilter;
import peipo.ru.cardealership.infrastructure.web.dto.CarFilterDto;

@Component
@RequiredArgsConstructor
public class CarFilterMapper
{
    public Filter<CarModel> toDomain(CarFilterDto carFilterDto)
    {
        List<Filter<CarModel>> filters = new ArrayList<>();

        if (carFilterDto.getBrand() != null)
        {
            filters.add(new BrandFilter(carFilterDto.getBrand()));
        }

        if (carFilterDto.getModel() != null)
        {
            filters.add(new ModelFilter(carFilterDto.getModel()));
        }

        if (carFilterDto.getGearBox() != null)
        {
            filters.add(new GearBoxFilter(carFilterDto.getGearBox()));
        }

        if (carFilterDto.getMinHorsePower() != null)
        {
            filters.add(new EnginePowerFilter(carFilterDto.getMinHorsePower()));
        }

        return new AndFilter(filters);
    }
}
