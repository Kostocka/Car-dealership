package peipo.ru.storage.domain.repository;

import peipo.ru.common.vo.id.PartId;

public interface ModelPartCompatibilityRepository
{
    boolean isCompatible(PartId partId, CarModelId carModelId);

    void add(PartId partId, CarModelId carModelId);
}
