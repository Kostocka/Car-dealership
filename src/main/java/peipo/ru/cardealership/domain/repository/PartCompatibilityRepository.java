package peipo.ru.cardealership.domain.repository;

import peipo.ru.cardealership.domain.vo.id.PartId;

public interface PartCompatibilityRepository
{
    boolean isCompatible(PartId firstPartId, PartId secondPartId);

    void addCompatibility(PartId firstPartId, PartId secondPartId);
}
