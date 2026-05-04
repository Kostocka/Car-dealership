package peipo.ru.order.domain.models.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;

@Getter
@AllArgsConstructor
public abstract class Order
{
    private final OrderId orderId;
    private final ClientId clientId;
    private final EmployeeId managerId;
}
