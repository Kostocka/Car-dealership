package peipo.ru.common.contracts.events.orders.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;

@Getter
@AllArgsConstructor
public class StockCarOrderCreatedEvent implements DomainEvent
{
    private final OrderId orderId;
    private final ClientId clientId;
    private final EmployeeId managerId;
    private final CarId carId;

    @Override
    public String eventType()
    {
        return "stock.order.created";
    }
}