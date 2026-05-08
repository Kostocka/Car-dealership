package peipo.ru.storage.application.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderCancelledEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderCreatedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderDeliveredEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderPaidEvent;
import peipo.ru.storage.application.services.ConfiguredAssemblyOrderService;

@Component
@AllArgsConstructor
public class ConfiguredOrderListener
{
    private final ConfiguredAssemblyOrderService configuredAssemblyOrderService;

    @EventListener
    public void handle(ConfiguredOrderCreatedEvent event)
    {
        configuredAssemblyOrderService.start(event.getOrderId(), event.getConfiguration());
    }

    @EventListener
    public void handle(ConfiguredOrderPaidEvent event)
    {
        configuredAssemblyOrderService.startDelivery(
                event.getOrderId()
        );
    }

    @EventListener
    public void handle(ConfiguredOrderCancelledEvent event)
    {
        configuredAssemblyOrderService.cancel(event.getOrderId());
    }

    @EventListener
    public void handle(ConfiguredOrderDeliveredEvent  event)
    {
        configuredAssemblyOrderService.completeDelivery(event.getOrderId());
    }
}
