package peipo.ru.storage.application.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.StockCarOrderCreatedEvent;
import peipo.ru.storage.domain.services.AssemblyOrderService;

@Component
@AllArgsConstructor
public class StockCarOrderListener
{

    private final AssemblyOrderService assemblyOrderService;

    @EventListener
    public void handle(StockCarOrderCreatedEvent event)
    {

        assemblyOrderService.create(event.getOrderId());
    }
}
