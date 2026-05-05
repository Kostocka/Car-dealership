package peipo.ru.order.domain.services;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peipo.ru.common.contracts.events.EventBus;
import peipo.ru.common.contracts.events.orders.configured.*;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.repository.ConfiguredOrderRepository;

@Service
@AllArgsConstructor
public class ConfiguredOrderService
{
    private ConfiguredOrderRepository configuredOrderRepository;
    private EmployeeAssignmentService employeeAssignmentService;
    private EventBus eventBus;

    @Transactional
    public ConfiguredCarOrder createConfiguredCarOrder(ClientId clientId, CarConfiguration configuration)
    {
        EmployeeId managerId = employeeAssignmentService.assignManager();

        ConfiguredCarOrder order =
                new ConfiguredCarOrder(OrderId.generate(), clientId, managerId, configuration);
        configuredOrderRepository.save(order);

        eventBus.publish(
                new ConfiguredOrderCreatedEvent(order.getOrderId(), clientId, configuration)
        );

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

    @Transactional
    public void cancelOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.cancel();
        configuredOrderRepository.save(configuredCarOrder);

        eventBus.publish(
                new ConfiguredOrderCancelledEvent(configuredCarOrder.getOrderId())
        );
    }

    @Transactional
    public void approveOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.approve();
        configuredOrderRepository.save(configuredCarOrder);

        eventBus.publish(
                new ConfiguredOrderApprovedEvent(configuredCarOrder.getOrderId())
        );
    }

    @Transactional
    public void payOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.pay();
        configuredOrderRepository.save(configuredCarOrder);

        eventBus.publish(
                new ConfiguredOrderPaidEvent(configuredCarOrder.getOrderId())
        );
    }

    @Transactional
    public void deliverOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.deliver();
        configuredOrderRepository.save(configuredCarOrder);

        eventBus.publish(
                new ConfiguredOrderDeliveredEvent(configuredCarOrder.getOrderId())
        );
    }

    @Transactional
    public void finishOrder(ConfiguredCarOrder configuredCarOrder)
    {
        configuredCarOrder.finish();
        configuredOrderRepository.save(configuredCarOrder);

        eventBus.publish(
                new ConfiguredOrderFinishedEvent(configuredCarOrder.getOrderId())
        );
    }
}
