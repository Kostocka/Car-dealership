package peipo.ru.storage.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.models.filters.parts.GearBoxFilter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class GearBoxFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof GearBoxFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        GearBoxFilter gearBoxFilter = (GearBoxFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("carModel").get("gearBox").get("gearBoxType"),
                        gearBoxFilter.getType()
                );
    }
}