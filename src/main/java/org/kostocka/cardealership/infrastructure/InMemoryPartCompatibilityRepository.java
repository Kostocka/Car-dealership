package org.kostocka.cardealership.infrastructure;

import java.util.HashSet;
import java.util.Set;
import org.kostocka.cardealership.domain.repository.PartCompatibilityRepository;
import org.kostocka.cardealership.domain.vo.id.PartId;

public class InMemoryPartCompatibilityRepository implements PartCompatibilityRepository
{
    private final Set<Pair<PartId, PartId>> compatibility = new HashSet<>();

    @Override
    public boolean isCompatible(PartId firstPartId, PartId secondPartId)
    {
        return compatibility.contains(new Pair<>(firstPartId, secondPartId));
    }

    @Override
    public void addCompatibility(PartId firstPartId, PartId secondPartId)
    {
        compatibility.add(new Pair<>(firstPartId, secondPartId));
        compatibility.add(new Pair<>(secondPartId, firstPartId));
    }
}

