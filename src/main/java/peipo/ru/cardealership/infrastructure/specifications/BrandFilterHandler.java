package peipo.ru.cardealership.infrastructure.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.models.filters.parts.BrandFilter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class BrandFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof BrandFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        BrandFilter brandFilter = (BrandFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("carModel").get("brand"), brandFilter.getBrand());
    }
}
