package peipo.ru.storage.infrastructure.persistence.mappers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;
import peipo.ru.storage.infrastructure.persistence.specifications.CompositeFilterHandler;
import peipo.ru.storage.infrastructure.persistence.specifications.FilterHandler;
import peipo.ru.storage.infrastructure.persistence.specifications.FilterResolver;

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
