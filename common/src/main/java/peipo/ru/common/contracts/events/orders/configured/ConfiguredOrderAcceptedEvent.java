package peipo.ru.common.contracts.events.orders.configured;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.OrderId;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ConfiguredOrderAcceptedEvent extends DomainEvent
{
    private final OrderId orderId;

    @Override
    public String eventType()
    {
        return "configured.order.accepted";
    }

    @Override
    public UUID aggregateId()
    {
        return orderId.id();
    }
}
