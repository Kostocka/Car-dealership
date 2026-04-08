package peipo.ru.cardealership.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.models.filters.parts.BodyFilter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class BodyFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof BodyFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        BodyFilter bodyFilter = (BodyFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("carModel").get("body").get("bodyType"), bodyFilter.getType());
    }
}