package peipo.ru.cardealership.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.repository.StockOrderRepository;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

public class InMemoryStockOrderRepository implements StockOrderRepository
{
    private final Map<OrderId, StockCarOrder> orders = new HashMap<>();

    @Override
    public List<StockCarOrder> findByClientId(ClientId clientId)
    {
        return orders.values().stream()
                .filter(order -> order.getClientId().equals(clientId))
                .toList();
    }

    @Override
    public List<StockCarOrder> findByManagerId(EmployeeId employeeId)
    {
        return orders.values().stream()
                .filter(order -> order.getManagerId().equals(employeeId))
                .toList();
    }

    @Override
    public Optional<StockCarOrder> findById(OrderId orderId)
    {
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public List<StockCarOrder> findAll()
    {
        return orders.values().stream()
                .toList();
    }

    @Override
    public void save(StockCarOrder entity)
    {
        orders.put(entity.getOrderId(), entity);
    }

    @Override
    public void delete(OrderId orderId)
    {
        orders.remove(orderId);
    }
}
