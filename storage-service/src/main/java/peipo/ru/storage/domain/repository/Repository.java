package peipo.ru.storage.domain.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, IDT>
{
    Optional<T> findById(IDT id);

    List<T> findAll();

    T save(T entity);

    void delete(IDT id);
}