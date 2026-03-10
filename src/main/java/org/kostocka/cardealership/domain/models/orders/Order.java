package org.kostocka.cardealership.domain.models.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;


@Getter
@AllArgsConstructor
public abstract class Order
{
    private final OrderId orderId;
    private final ClientId clientId;
    private final EmployeeId managerId;

    private final Money price;

    @Setter
    private OrderState state;
}
