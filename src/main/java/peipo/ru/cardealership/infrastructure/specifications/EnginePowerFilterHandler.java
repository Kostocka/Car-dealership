package peipo.ru.cardealership.infrastructure.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.models.filters.parts.EnginePowerFilter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class EnginePowerFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof EnginePowerFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        EnginePowerFilter filter = (EnginePowerFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("carModel").get("engine").get("power"), filter.getMinPower());
    }
}