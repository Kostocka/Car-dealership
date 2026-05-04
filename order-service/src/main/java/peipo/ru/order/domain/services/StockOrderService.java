package peipo.ru.order.domain.services;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.contracts.events.EventBus;
import peipo.ru.common.contracts.events.StockCarOrderCreatedEvent;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.domain.repository.StockOrderRepository;

@Service
@AllArgsConstructor
public class StockOrderService
{
    private StockOrderRepository stockOrderRepository;
    private EmployeeAssignmentService employeeAssignmentService;
    private EventBus eventBus;

    public StockCarOrder createStockOrder(ClientId clientId, CarId carId)
    {
        EmployeeId manager = employeeAssignmentService.assignManager();

        StockCarOrder order = new StockCarOrder(OrderId.generate(), clientId, manager, carId);
        stockOrderRepository.save(order);

        eventBus.publish(
                new StockCarOrderCreatedEvent(
                        order.getOrderId(),
                        clientId,
                        manager,
                        carId
                )
        );

        return order;
    }

    public Optional<StockCarOrder> getStockCarOrderById(OrderId orderId)
    {
        return stockOrderRepository.findById(orderId);
    }

    public List<StockCarOrder> getAllStockCarOrders()
    {
        return stockOrderRepository.findAll();
    }

    public List<StockCarOrder> getStockCarOrderByClient(ClientId clientId)
    {
        return stockOrderRepository.findByClientId(clientId);
    }

    public void cancelOrder(StockCarOrder stockCarOrder)
    {
        stockCarOrder.cancel();
        stockOrderRepository.save(stockCarOrder);
    }

    public void approveOrder(StockCarOrder stockCarOrder)
    {
        stockCarOrder.approve();
        stockOrderRepository.save(stockCarOrder);
    }

    public void payOrder(StockCarOrder stockCarOrder)
    {
        stockCarOrder.pay();
        stockOrderRepository.save(stockCarOrder);
    }

    public void finishOrder(StockCarOrder stockCarOrder)
    {
        stockCarOrder.finish();
        stockOrderRepository.save(stockCarOrder);
    }
}
