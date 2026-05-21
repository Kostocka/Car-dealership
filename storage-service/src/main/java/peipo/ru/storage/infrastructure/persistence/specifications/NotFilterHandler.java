package peipo.ru.storage.infrastructure.persistence.specifications;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.models.filters.NotFilter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

@Component
@RequiredArgsConstructor
public class NotFilterHandler implements CompositeFilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof NotFilter<?>;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec, FilterResolver filterResolver)
    {
        NotFilter<?> filter = (NotFilter<?>) spec;

        return Specification.not(filterResolver.resolve(filter));
    }
}