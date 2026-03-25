package peipo.ru.cardealership.domain.repository;

import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.PartId;

public interface ModelPartCompatibilityRepository
{
    boolean isCompatible(PartId partId, CarModelId carModelId);

    void add(PartId partId, CarModelId carModelId);
}
