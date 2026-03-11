package org.kostocka.cardealership.domain.models.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrSpecification<T> implements Specification<T>
{
    private final List<Specification<T>> specifications;

    @Override
    public boolean isSatisfiedBy(T entity)
    {
        for (Specification<T> specification : specifications)
        {
            if (specification.isSatisfiedBy(entity))
            {
                return true;
            }
        }
        return false;
    }
}
