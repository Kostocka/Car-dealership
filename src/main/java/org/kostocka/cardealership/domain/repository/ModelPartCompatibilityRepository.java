package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.vo.id.CarModelId;
import org.kostocka.cardealership.domain.vo.id.PartId;

public interface ModelPartCompatibilityRepository
{
    boolean isCompatible(PartId partId, CarModelId carModelId);
}
