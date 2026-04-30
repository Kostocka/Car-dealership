package peipo.ru.storage.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

public interface FilterResolver
{
    Specification<CarEntity> resolve(Filter<?> filter);
}
