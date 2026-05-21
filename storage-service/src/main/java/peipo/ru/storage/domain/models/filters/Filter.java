package peipo.ru.storage.domain.models.filters;

public interface Filter<T>
{
    boolean isSatisfiedBy(T entity);
}
