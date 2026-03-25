package peipo.ru.cardealership.domain.models.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotSpecification<T>  implements Specification<T>
{
    private final Specification<T> specification;

    @Override
    public boolean isSatisfiedBy(T entity)
    {
        return !specification.isSatisfiedBy(entity);
    }
}
