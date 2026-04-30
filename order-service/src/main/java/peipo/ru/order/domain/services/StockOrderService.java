package peipo.ru.order.domain.services;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.order.domain.models.Car;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.domain.repository.CarRepository;
import peipo.ru.order.domain.repository.StockOrderRepository;
import peipo.ru.order.domain.vo.id.ClientId;
import peipo.ru.order.domain.vo.id.EmployeeId;
import peipo.ru.order.domain.vo.id.OrderId;

@Service
@AllArgsConstructor
public class StockOrderService
{
    private StockOrderRepository stockOrderRepository;
    private EmployeeAssignmentService employeeAssignmentService;

    public StockCarOrder createStockOrder(ClientId clientId, CarId carId)
    {
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car not found")
        );

        EmployeeId manager = employeeAssignmentService.assignManager();

        StockCarOrder order = new StockCarOrder(OrderId.generate(), clientId, manager, car.getCarId());
        stockOrderRepository.save(order);
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
