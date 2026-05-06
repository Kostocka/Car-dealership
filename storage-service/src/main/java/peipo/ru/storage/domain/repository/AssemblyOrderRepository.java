package peipo.ru.storage.domain.repository;

import peipo.ru.storage.domain.models.AssemblyOrder;

public interface AssemblyOrderRepository
{
    void save(AssemblyOrder order);
}
