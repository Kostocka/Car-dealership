package peipo.ru.storage.domain.repository;

import java.util.Optional;
import java.util.UUID;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.ConfiguredAssemblyOrder;

public interface ConfiguredAssemblyOrderRepository extends Repository<ConfiguredAssemblyOrder, UUID>
{
    Optional<ConfiguredAssemblyOrder> findByOrderId(OrderId orderId);
}
