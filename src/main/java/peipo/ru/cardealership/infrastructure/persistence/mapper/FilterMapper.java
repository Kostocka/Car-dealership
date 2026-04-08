package peipo.ru.cardealership.infrastructure.persistence.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;
import peipo.ru.cardealership.infrastructure.persistence.specifications.CompositeFilterHandler;
import peipo.ru.cardealership.infrastructure.persistence.specifications.FilterHandler;
import peipo.ru.cardealership.infrastructure.persistence.specifications.FilterResolver;

@Component
@RequiredArgsConstructor
public class FilterMapper implements FilterResolver
{
    private final List<FilterHandler> handlers;
    private final List<CompositeFilterHandler> compositeHandlers;

    public Specification<CarEntity> resolve(Filter<?> filter)
    {
        for (CompositeFilterHandler compositeHandler : compositeHandlers)
        {
            if (compositeHandler.supports(filter))
            {
                return compositeHandler.handle(filter, this);
            }
        }

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
