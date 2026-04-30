package peipo.ru.storage.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.models.filters.OrFilter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class OrFilterHandler implements CompositeFilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof OrFilter<?>;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec, FilterResolver filterResolver)
    {
        OrFilter<?> orFilter = (OrFilter<?>) spec;
        Specification<CarEntity> result = Specification.where(null);

        for (Filter<?> filter : orFilter.getFilters())
        {
            result = result.and(filterResolver.resolve(filter));
        }

        return result;
    }
}
