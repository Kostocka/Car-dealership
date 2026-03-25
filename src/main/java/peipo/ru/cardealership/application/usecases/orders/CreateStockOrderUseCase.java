package peipo.ru.cardealership.application.usecases.orders;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.exception.EntityNotFoundException;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.repository.CarRepository;
import peipo.ru.cardealership.domain.repository.StockOrderRepository;
import peipo.ru.cardealership.domain.services.EmployeeAssignmentService;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

@AllArgsConstructor
public class CreateStockOrderUseCase
{
    private StockOrderRepository  stockOrderRepository;
    private CarRepository carRepository;
    private EmployeeAssignmentService  employeeAssignmentService;

    public StockCarOrder execute(ClientId clientId, CarId carId)
    {
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car not found")
        );

        EmployeeId manager = employeeAssignmentService.assignManager();

        StockCarOrder order = new StockCarOrder(OrderId.generate(), clientId, manager, car.getCarId());
        stockOrderRepository.save(order);
        return order;
    }
}
