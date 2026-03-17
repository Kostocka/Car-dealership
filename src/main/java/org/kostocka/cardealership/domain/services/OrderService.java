package org.kostocka.cardealership.domain.services;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.exception.EntityNotFoundException;
import org.kostocka.cardealership.domain.models.Car;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.models.orders.StockCarOrder;
import org.kostocka.cardealership.domain.repository.CarRepository;
import org.kostocka.cardealership.domain.repository.ConfiguredOrderRepository;
import org.kostocka.cardealership.domain.repository.StockOrderRepository;
import org.kostocka.cardealership.domain.vo.id.CarId;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

@AllArgsConstructor
public class OrderService
{
    private final ConfiguredOrderRepository configuredOrderRepository;
    private final StockOrderRepository stockOrderRepository;
    private final CarRepository carRepository;

    private final EmployeeAssignmentService employeeAssignmentService;
    private final ConfiguratorService configuratorService;

    public ConfiguredCarOrder createConfiguredOrder(ClientId clientId, CarModel configuration)
    {
        configuratorService.validateConfiguration(configuration);

        OrderId orderId = OrderId.generate();
        EmployeeId managerId = employeeAssignmentService.assignManager();

        ConfiguredCarOrder order = new ConfiguredCarOrder(orderId, clientId, managerId, configuration);
        configuredOrderRepository.save(order);
        return order;
    }

    public StockCarOrder createStockOrder(ClientId clientId, CarId carId)
    {
        OrderId orderId = OrderId.generate();
        EmployeeId managerId = employeeAssignmentService.assignManager();
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        StockCarOrder order = new StockCarOrder(orderId, clientId, managerId, car.getCarId());
        stockOrderRepository.save(order);
        return order;
    }
}
