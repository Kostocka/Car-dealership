package peipo.ru.storage.domain.repository;

import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.parts.Part;

public interface PartRepository<T extends Part> extends Repository<T, PartId>
{}