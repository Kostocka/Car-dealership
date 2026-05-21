package peipo.ru.storage.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.models.filters.parts.FuelTypeFilter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class FuelTypeFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof FuelTypeFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        FuelTypeFilter fuelTypeFilter = (FuelTypeFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("carModel").get("engine").get("fuelType").get("fuelTypeType"),
                        fuelTypeFilter.getType()
                );
    }
}