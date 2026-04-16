package peipo.ru.cardealership.domain.models.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFilter<T>  implements Filter<T>
{
    private final Filter<T> filter;

    @Override
    public boolean isSatisfiedBy(T entity)
    {
        return !filter.isSatisfiedBy(entity);
    }
}
