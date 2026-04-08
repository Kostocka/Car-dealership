package peipo.ru.cardealership.infrastructure.specifications;

import org.springframework.data.jpa.domain.Specification;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

public interface FilterHandler
{
    boolean supports(Filter<?> filter);

    Specification<CarEntity> handle(Filter<?> spec);
}