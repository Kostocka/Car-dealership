package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.models.parts.Part;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.repository.PartRepository;
import org.kostocka.cardealership.domain.vo.id.PartId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryPartRepository<T extends Part> implements PartRepository<T>
{
    private final Map<PartId, T> parts = new HashMap<>();

    @Override
    public List<T> find(Specification<T> filter) {
        return parts.values()
                .stream()
                .filter(filter::isSatisfiedBy)
                .toList();
    }

    @Override
    public Optional<T> findById(PartId partId) {
        return Optional.ofNullable(parts.get(partId));
    }

    @Override
    public List<T> findAll() {
        return parts.values().stream().toList();
    }

    @Override
    public void save(T entity) {
        parts.put(entity.getId(), entity);
    }

    @Override
    public void delete(PartId partId) {
        parts.remove(partId);
    }
}
