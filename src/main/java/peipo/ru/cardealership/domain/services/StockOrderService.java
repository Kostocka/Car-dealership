package peipo.ru.cardealership.domain.services;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.exception.EntityNotFoundException;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.repository.CarRepository;
import peipo.ru.cardealership.domain.repository.StockOrderRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

@Service
@RequiredArgsConstructor
public class StockOrderService
{
    private StockOrderRepository stockOrderRepository;
    private CarRepository carRepository;
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
    }

    public void payOrder(StockCarOrder stockCarOrder)
    {
        stockCarOrder.pay();
    }

    public void finishOrder(StockCarOrder stockCarOrder)
    {
        stockCarOrder.finish();
    }
}
