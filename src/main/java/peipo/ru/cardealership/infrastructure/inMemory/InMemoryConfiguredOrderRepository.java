package peipo.ru.cardealership.infrastructure.inMemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.repository.ConfiguredOrderRepository;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

public class InMemoryConfiguredOrderRepository implements ConfiguredOrderRepository
{
    private final Map<OrderId, ConfiguredCarOrder> orders = new HashMap<>();

    @Override
    public List<ConfiguredCarOrder> findByClientId(ClientId clientId)
    {
        return orders.values().stream().filter(order -> order.getClientId().equals(clientId)).toList();
    }

    @Override
    public List<ConfiguredCarOrder> findByManagerId(EmployeeId employeeId)
    {
        return orders.values().stream().filter(order -> order.getManagerId().equals(employeeId)).toList();
    }

    @Override
    public Optional<ConfiguredCarOrder> findById(OrderId orderId)
    {
        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public List<ConfiguredCarOrder> findAll()
    {
        return orders.values().stream().toList();
    }

    @Override
    public void save(ConfiguredCarOrder entity)
    {
        orders.put(entity.getOrderId(), entity);
    }

    @Override
    public void delete(OrderId orderId)
    {
        orders.remove(orderId);
    }
}
