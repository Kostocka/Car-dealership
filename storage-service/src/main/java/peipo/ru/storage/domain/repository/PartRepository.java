package peipo.ru.storage.domain.repository;

import peipo.ru.storage.domain.models.parts.Part;
import peipo.ru.common.vo.id.PartId;

public interface PartRepository<T extends Part> extends Repository<T, PartId>
{}