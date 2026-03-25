package peipo.ru.cardealership.domain.models.specifications;

public interface Specification<T>
{
    boolean isSatisfiedBy(T entity);
}
