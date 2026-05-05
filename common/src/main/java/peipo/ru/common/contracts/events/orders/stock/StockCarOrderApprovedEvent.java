package peipo.ru.common.contracts.events.orders.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.OrderId;

@Getter
@AllArgsConstructor
public class StockCarOrderApprovedEvent implements DomainEvent
{
    private final OrderId orderId;

    @Override
    public String eventType()
    {
        return "stock.order.approved";
    }
}