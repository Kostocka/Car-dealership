package peipo.ru.order.application.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderRejectedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarReadyForPickupEvent;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.domain.repository.StockOrderRepository;

@Component
@AllArgsConstructor
public class StockOrderListener
{
    private final StockOrderRepository repository;

    @EventListener
    public void handle(StockCarOrderAcceptedEvent event)
    {
        StockCarOrder order =
                repository.findById(event.getOrderId())
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Order not found"
                                )
                        );

        order.approve();

        repository.save(order);
    }

    @EventListener
    public void handle(StockCarOrderRejectedEvent event)
    {
        StockCarOrder order =
                repository.findById(event.getOrderId())
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Order not found"
                                )
                        );

        order.cancel();
        order.setCancellationReason(event.getReason());

        repository.save(order);
    }

    @EventListener
    public void handle(StockCarReadyForPickupEvent event)
    {
        StockCarOrder order =
                repository.findById(event.getOrderId())
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Order not found"
                                )
                        );

        order.pickup();

        repository.save(order);
    }
}
