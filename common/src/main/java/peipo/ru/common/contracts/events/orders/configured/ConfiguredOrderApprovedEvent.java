package peipo.ru.common.contracts.events.orders.configured;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.OrderId;

@Getter
@AllArgsConstructor
public class ConfiguredOrderApprovedEvent implements DomainEvent
{
    private final OrderId orderId;

    @Override
    public String eventType()
    {
        return "configured.order.approved";
    }
}
