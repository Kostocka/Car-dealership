package peipo.ru.cardealership.infrastructure.specifications;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.infrastructure.mapper.FilterSpecificationMapper;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Component
@RequiredArgsConstructor
public class FilterMapper implements FilterResolver
{
    private final List<FilterHandler> handlers;

    public Specification<CarEntity> resolve(Filter<?> filter)
    {
        for (FilterHandler handler : handlers)
        {
            if (handler.supports(filter))
            {
                return handler.handle(filter);
            }
        }

        throw new DomainValidationException("No handler for filter " + filter.getClass());
    }
}
