package peipo.ru.order.domain.services;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.order.domain.models.CarModel;
import peipo.ru.order.domain.models.CarConfiguration;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.repository.ConfiguredOrderRepository;
import peipo.ru.order.domain.vo.id.ClientId;
import peipo.ru.order.domain.vo.id.EmployeeId;
import peipo.ru.order.domain.vo.id.OrderId;
import peipo.ru.storage.domain.services.ConfiguratorService;
import peipo.ru.storage.domain.services.InventoryService;

@Service
@AllArgsConstructor
public class ConfiguredOrderService
{
    private ConfiguredOrderRepository configuredOrderRepository;
    private EmployeeAssignmentService employeeAssignmentService;

    public ConfiguredCarOrder createConfiguredCarOrder(ClientId clientId, CarConfiguration configuration)
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
