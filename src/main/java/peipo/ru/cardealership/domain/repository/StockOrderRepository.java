package peipo.ru.cardealership.domain.repository;

import java.util.List;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

public interface StockOrderRepository extends Repository<StockCarOrder, OrderId>
{
    List<StockCarOrder> findByClientId(ClientId clientId);
}