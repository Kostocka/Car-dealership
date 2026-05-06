package peipo.ru.order.application.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderRejectedEvent;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.repository.ConfiguredOrderRepository;

@Component
@AllArgsConstructor
public class ConfiguredOrderListener
{
    private final ConfiguredOrderRepository repository;

    @EventListener
    public void handle(ConfiguredOrderAcceptedEvent event)
    {
        ConfiguredCarOrder order = repository.findById(event.getOrderId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with id " + event.getOrderId() + " not found")
                );

        order.approve();

        repository.save(order);
    }

    @EventListener
    public void handle(ConfiguredOrderRejectedEvent event)
    {
        ConfiguredCarOrder order = repository.findById(event.getOrderId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Order with id " + event.getOrderId() + " not found")
                );

        order.cancel();

        repository.save(order);
    }
}
