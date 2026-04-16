package peipo.ru.cardealership.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

public interface FilterResolver
{
    Specification<CarEntity> resolve(Filter<?> filter);
}
