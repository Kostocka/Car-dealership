package peipo.ru.common.contracts.events.orders.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockCarOrderCreatedEvent extends DomainEvent
{
    private OrderId orderId;
    private ClientId clientId;
    private EmployeeId managerId;
    private CarId carId;

    @Override
    public UUID aggregateId()
    {
        return orderId.id();
    }

    @Override
    public String eventType()
    {
        return "stock.order.created";
    }
}