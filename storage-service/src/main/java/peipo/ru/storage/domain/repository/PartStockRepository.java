package peipo.ru.storage.domain.repository;

import peipo.ru.common.vo.id.PartId;

public interface PartStockRepository
{
    int getQuantity(PartId partId);

    void increase(PartId partId, int quantity);

    void decrease(PartId partId, int quantity);
}
