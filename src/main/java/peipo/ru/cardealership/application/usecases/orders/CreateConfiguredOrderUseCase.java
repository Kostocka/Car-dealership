package peipo.ru.cardealership.application.usecases.orders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.repository.ConfiguredOrderRepository;
import peipo.ru.cardealership.domain.services.ConfiguratorService;
import peipo.ru.cardealership.domain.services.EmployeeAssignmentService;
import peipo.ru.cardealership.domain.services.InventoryService;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

@Service
@AllArgsConstructor
public class CreateConfiguredOrderUseCase
{
    private ConfiguredOrderRepository  configuredOrderRepository;
    private EmployeeAssignmentService employeeAssignmentService;
    private ConfiguratorService  configuratorService;
    private InventoryService inventoryService;

    public ConfiguredCarOrder execute(ClientId clientId, CarModel configuration)
    {
        configuratorService.validateConfiguration(configuration);
        inventoryService.checkAvailability(configuration);

        inventoryService.reserveParts(configuration);

        OrderId orderId = OrderId.generate();
        EmployeeId managerId = employeeAssignmentService.assignManager();

        ConfiguredCarOrder order = new ConfiguredCarOrder(orderId, clientId, managerId, configuration);
        configuredOrderRepository.save(order);
        return order;
    }
}
