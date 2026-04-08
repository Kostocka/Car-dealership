package peipo.ru.cardealership.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.filters.AndFilter;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class AndFilterHandler implements CompositeFilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof AndFilter<?>;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec, FilterResolver filterResolver)
    {
        AndFilter<?> andFilter = (AndFilter<?>) spec;
        Specification<CarEntity> result = Specification.where(null);

        for (Filter<?> filter : andFilter.getFilters())
        {
            result = result.and(filterResolver.resolve(filter));
        }

        return result;
    }
}
