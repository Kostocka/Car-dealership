package peipo.ru.storage.application.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderCancelledEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderCreatedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderPaidEvent;
import peipo.ru.storage.application.services.StockAssemblyOrderService;

@Component
@AllArgsConstructor
public class StockOrderListener
{
    private final StockAssemblyOrderService service;

    @EventListener
    public void handle(StockCarOrderCreatedEvent event)
    {
        service.start(
                event.getOrderId(),
                event.getCarId()
        );
    }

    @EventListener
    public void handle(StockCarOrderPaidEvent event)
    {
        service.completeDelivery(
                event.getOrderId()
        );
    }

    @EventListener
    public void handle(StockCarOrderCancelledEvent event)
    {
        service.cancel(
                event.getOrderId()
        );
    }
}
