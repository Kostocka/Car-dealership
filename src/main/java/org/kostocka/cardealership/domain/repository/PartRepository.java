package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.models.parts.Part;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.vo.id.PartId;

import java.util.List;

public interface PartRepository<T extends Part> extends Repository<T, PartId>
{
    List<T> find(Specification<T> filter);
}
