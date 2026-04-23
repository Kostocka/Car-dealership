package peipo.ru.cardealership.domain.services;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.repository.ConfiguredOrderRepository;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

@Service
@AllArgsConstructor
public class ConfiguredOrderService
{
    private ConfiguredOrderRepository configuredOrderRepository;
    private EmployeeAssignmentService employeeAssignmentService;
    private ConfiguratorService configuratorService;
    private InventoryService inventoryService;

    public ConfiguredCarOrder createConfiguredCarOrder(ClientId clientId, CarModel configuration)
    {
        configuratorService.validateConfiguration(configuration);
        inventoryService.checkAvailability(configuration);

        inventoryService.reserveParts(configuration);

        EmployeeId managerId = employeeAssignmentService.assignManager();

        ConfiguredCarOrder order =
                new ConfiguredCarOrder(OrderId.generate(), clientId, managerId, configuration);
        configuredOrderRepository.save(order);
        return order;
    }

    public Optional<ConfiguredCarOrder> getConfiguredCarOrderById(OrderId orderId)
    {
        return configuredOrderRepository.findById(orderId);
    }

    public List<ConfiguredCarOrder> getAllConfiguredCarOrders()
    {
        return configuredOrderRepository.findAll();
    }

    public List<ConfiguredCarOrder> getConfiguredCarOrderByClient(ClientId clientId)
    {
        return configuredOrderRepository.findByClientId(clientId);
    }

    public void cancelOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.cancel();
        configuredOrderRepository.save(configuredCarOrder);
    }

    public void approveOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.approve();
        configuredOrderRepository.save(configuredCarOrder);
    }

    public void payOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.pay();
        configuredOrderRepository.save(configuredCarOrder);
    }

    public void deliverOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.deliver();
        configuredOrderRepository.save(configuredCarOrder);
    }

    public void finishOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.finish();
        configuredOrderRepository.save(configuredCarOrder);
    }
}
