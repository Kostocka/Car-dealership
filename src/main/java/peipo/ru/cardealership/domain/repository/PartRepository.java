package peipo.ru.cardealership.domain.repository;

import peipo.ru.cardealership.domain.models.parts.Part;
import peipo.ru.cardealership.domain.vo.id.PartId;

public interface PartRepository<T extends Part> extends Repository<T, PartId>
{
}