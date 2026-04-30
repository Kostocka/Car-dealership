package peipo.ru.storage.domain.models.filters;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrFilter<T> implements Filter<T>
{
    private final List<Filter<T>> filters;

    @Override
    public boolean isSatisfiedBy(T entity)
    {
        for (Filter<T> filter : filters)
        {
            if (filter.isSatisfiedBy(entity))
            {
                return true;
            }
        }
        return false;
    }
}
