package org.kostocka.cardealership.domain.models.specifications;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AndSpecification<T> implements Specification<T>
{
    private final List<Specification<T>> specifications;

    @Override
    public boolean isSatisfiedBy(T entity)
    {
        for (Specification<T> specification : specifications)
        {
            if (!specification.isSatisfiedBy(entity))
            {
                return false;
            }
        }
        return true;
    }
}
