package peipo.ru.cardealership.infrastructure.inMemory;

import java.util.HashSet;
import java.util.Set;
import peipo.ru.cardealership.domain.repository.ModelPartCompatibilityRepository;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.PartId;

public class InMemoryModelPartCompatibilityRepository implements ModelPartCompatibilityRepository
{
    private final Set<Pair<PartId, CarModelId>> modelsRules = new HashSet<>();

    @Override
    public boolean isCompatible(PartId partId, CarModelId carModelId)
    {
        return modelsRules.contains(new Pair<>(partId, carModelId));
    }

    @Override
    public void add(PartId partId, CarModelId carModelId)
    {
        modelsRules.add(new Pair<>(partId, carModelId));
    }
}
