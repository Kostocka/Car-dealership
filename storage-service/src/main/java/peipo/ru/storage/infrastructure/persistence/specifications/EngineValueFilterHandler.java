package peipo.ru.storage.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.models.filters.parts.EngineValueFilter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

@Component
public class EngineValueFilterHandler implements FilterHandler
{
    @Override
    public boolean supports(Filter<?> filter)
    {
        return filter instanceof EngineValueFilter;
    }

    @Override
    public Specification<CarEntity> handle(Filter<?> spec)
    {
        EngineValueFilter filter = (EngineValueFilter) spec;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("carModel").get("engine").get("volume"),
                        filter.getMinVolume()
                );
    }
}