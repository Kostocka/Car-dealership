package peipo.ru.cardealership.infrastructure.inMemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import peipo.ru.cardealership.domain.models.parts.Part;
import peipo.ru.cardealership.domain.repository.PartRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;

public class InMemoryPartRepository<T extends Part> implements PartRepository<T>
{
    private final Map<PartId, T> parts = new HashMap<>();

    @Override
    public Optional<T> findById(PartId partId)
    {
        return Optional.ofNullable(parts.get(partId));
    }

    @Override
    public List<T> findAll()
    {
        return parts.values().stream().toList();
    }

    @Override
    public void save(T entity)
    {
        parts.put(entity.getId(), entity);
    }

    @Override
    public void delete(PartId partId)
    {
        parts.remove(partId);
    }
}