package peipo.ru.order.domain.repository;

import java.util.List;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.OrderId;

public interface ConfiguredOrderRepository extends Repository<ConfiguredCarOrder, OrderId>
{
    List<ConfiguredCarOrder> findByClientId(ClientId clientId);
}