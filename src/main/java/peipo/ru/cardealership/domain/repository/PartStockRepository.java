package peipo.ru.cardealership.domain.repository;

import peipo.ru.cardealership.domain.vo.id.PartId;

public interface PartStockRepository
{
    int getQuantity(PartId partId);

    void increase(PartId partId, int quantity);

    void decrease(PartId partId, int quantity);
}
