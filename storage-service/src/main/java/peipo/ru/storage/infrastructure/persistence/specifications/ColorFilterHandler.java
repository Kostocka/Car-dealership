package peipo.ru.storage.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.models.filters.parts.ColorFilter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class ColorFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof ColorFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        ColorFilter colorFilter = (ColorFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("carModel").get("color"), colorFilter.getColor());
    }
}
