package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.repository.ModelPartCompatibilityRepository;
import org.kostocka.cardealership.domain.vo.id.CarModelId;
import org.kostocka.cardealership.domain.vo.id.PartId;

import java.util.HashMap;
import java.util.Map;

public class InMemoryModelPartCompatibilityRepository implements ModelPartCompatibilityRepository
{
    private final Map<PartId, CarModelId> modelsRules = new HashMap<>();

    @Override
    public boolean isCompatible(PartId partId, CarModelId carModelId) {
        return modelsRules.get(partId).equals(carModelId);
    }

    @Override
    public void add(PartId partId, CarModelId carModelId) {
        modelsRules.put(partId, carModelId);
    }
}
