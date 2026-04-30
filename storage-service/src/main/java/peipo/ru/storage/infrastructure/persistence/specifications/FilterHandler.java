package peipo.ru.storage.infrastructure.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

public interface FilterHandler
{
    boolean supports(Filter<?> filter);

    Specification<CarEntity> handle(Filter<?> spec);
}