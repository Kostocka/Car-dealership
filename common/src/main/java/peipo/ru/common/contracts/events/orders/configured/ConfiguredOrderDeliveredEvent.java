package peipo.ru.common.contracts.events.orders.configured;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.OrderId;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConfiguredOrderDeliveredEvent extends DomainEvent
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
        return "configured.order.delivered";
    }
}