package peipo.ru.cardealership.domain.models.filters;

public interface Filter<T>
{
    boolean isSatisfiedBy(T entity);
}
