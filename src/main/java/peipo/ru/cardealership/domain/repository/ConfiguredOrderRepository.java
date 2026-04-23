package peipo.ru.cardealership.domain.repository;

import java.util.List;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

public interface ConfiguredOrderRepository extends Repository<ConfiguredCarOrder, OrderId>
{
    List<ConfiguredCarOrder> findByClientId(ClientId clientId);
}