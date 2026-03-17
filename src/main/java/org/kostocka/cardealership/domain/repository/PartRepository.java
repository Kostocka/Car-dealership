package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.models.parts.Part;
import org.kostocka.cardealership.domain.vo.id.PartId;

public interface PartRepository<T extends Part> extends Repository<T, PartId>
{
}