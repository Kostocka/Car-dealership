package peipo.ru.common.contracts.events.orders.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.OrderId;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class StockCarOrderRejectedEvent implements DomainEvent
{
    private final OrderId orderId;
    private final String reason;

    @Override
    public UUID aggregateId()
    {
        return orderId.id();
    }

    @Override
    public String eventType()
    {
        return "stock.order.rejected";
    }
}
