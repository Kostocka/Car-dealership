package peipo.ru.cardealership.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.models.filters.parts.ModelFilter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class ModelFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof ModelFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        ModelFilter modelFilter = (ModelFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("carModel").get("model"), modelFilter.getModel());
    }
}
