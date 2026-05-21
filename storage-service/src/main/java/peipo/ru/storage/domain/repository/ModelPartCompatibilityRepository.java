package peipo.ru.storage.domain.repository;

import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.vo.CarModelId;

public interface ModelPartCompatibilityRepository
{
    boolean isCompatible(PartId partId, CarModelId carModelId);

    void add(PartId partId, CarModelId carModelId);
}
