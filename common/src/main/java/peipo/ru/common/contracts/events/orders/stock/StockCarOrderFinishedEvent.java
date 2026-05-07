package peipo.ru.common.contracts.events.orders.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.OrderId;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockCarOrderFinishedEvent extends DomainEvent
{
    private OrderId orderId;

    @Override
    public UUID aggregateId()
    {
        return orderId.id();
    }

    @Override
    public String eventType()
    {
        return "stock.order.finished";
    }
}
