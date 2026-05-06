package peipo.ru.storage.domain.repository;

import java.util.Optional;
import java.util.UUID;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.AssemblyOrder;

public interface AssemblyOrderRepository extends Repository<AssemblyOrder, UUID>
{
    Optional<AssemblyOrder> findByOrderId(OrderId orderId);
}
