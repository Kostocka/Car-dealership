package peipo.ru.order.domain.repository;

import java.util.List;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.OrderId;

public interface StockOrderRepository extends Repository<StockCarOrder, OrderId>
{
    List<StockCarOrder> findByClientId(ClientId clientId);
}