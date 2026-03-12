package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.repository.PartCompatibilityRepository;
import org.kostocka.cardealership.domain.vo.id.PartId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPartCompatibilityRepository implements PartCompatibilityRepository
{
    private final Map<PartId, PartId> compatibility = new ConcurrentHashMap<>();

    @Override
    public boolean isCompatible(PartId firstPartId, PartId secondPartId) {
        return compatibility.get(firstPartId).equals(secondPartId);
    }

    @Override
    public void addCompatibility(PartId firstPartId, PartId secondPartId) {
        compatibility.put(firstPartId, secondPartId);
        compatibility.put(secondPartId, firstPartId);
    }
}

