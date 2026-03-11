package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.vo.id.PartId;

public interface PartCompatibilityRepository
{
    boolean isCompatible(PartId firstPartId, PartId secondPartId);
}
