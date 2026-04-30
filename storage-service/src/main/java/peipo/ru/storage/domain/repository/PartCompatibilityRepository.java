package peipo.ru.storage.domain.repository;

import peipo.ru.common.vo.id.PartId;

public interface PartCompatibilityRepository
{
    boolean isCompatible(PartId firstPartId, PartId secondPartId);

    void addCompatibility(PartId firstPartId, PartId secondPartId);
}
