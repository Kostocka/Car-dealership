package peipo.ru.order.domain.models.orders;

import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;

@Getter
public abstract class Order
{
    private final OrderId orderId;
    private final ClientId clientId;
    private final EmployeeId managerId;

    @Setter
    private String cancellationReason;

    protected Order(OrderId orderId, ClientId clientId, EmployeeId managerId)
    {
        this.orderId = orderId;
        this.clientId = clientId;
        this.managerId = managerId;
    }
}
