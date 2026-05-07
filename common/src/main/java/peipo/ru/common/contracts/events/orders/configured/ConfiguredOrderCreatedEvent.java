package peipo.ru.common.contracts.events.orders.configured;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.OrderId;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConfiguredOrderCreatedEvent extends DomainEvent
{
    private OrderId orderId;
    private ClientId clientId;
    private CarConfiguration configuration;

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
