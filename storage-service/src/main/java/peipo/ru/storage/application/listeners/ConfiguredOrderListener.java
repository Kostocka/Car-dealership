package peipo.ru.storage.application.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderCancelledEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderCreatedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderPaidEvent;
import peipo.ru.storage.application.mappers.CarConfigurationMapper;
import peipo.ru.storage.application.services.AssemblyOrderService;

@Component
@AllArgsConstructor
public class ConfiguredOrderListener
{
    private final AssemblyOrderService assemblyOrderService;
    private final CarConfigurationMapper mapper;

    @EventListener
    public void handle(ConfiguredOrderCreatedEvent event)
    {
        assemblyOrderService.start(event.getOrderId(), event.getConfiguration());
    }

    @EventListener
    public void handle(ConfiguredOrderPaidEvent event)
    {
        assemblyOrderService.startDelivery(
                event.getOrderId()
        );
    }

    @EventListener
    public void handle(ConfiguredOrderCancelledEvent event)
    {
        assemblyOrderService.cancel(event.getOrderId());
    }
}
