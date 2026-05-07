package peipo.ru.common.contracts.events.orders.configured;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.OrderId;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ConfiguredOrderCreatedEvent extends DomainEvent
{
    private final OrderId orderId;
    private final ClientId clientId;
    private final CarConfiguration configuration;

    @Override
    public UUID aggregateId()
    {
        return orderId.id();
    }

    @Override
    public String eventType()
    {
        return "configured.order.created";
    }
}
