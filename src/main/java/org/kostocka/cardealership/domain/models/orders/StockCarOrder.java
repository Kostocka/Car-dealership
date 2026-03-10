package org.kostocka.cardealership.domain.models.orders;

import lombok.Getter;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.CarId;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

@Getter
public class StockCarOrder extends Order
{
    private final CarId carId;

    public StockCarOrder(OrderId orderId, ClientId clientId, EmployeeId managerId, Money price, OrderState state, CarId carId)
    {
        super(orderId, clientId, managerId, price, state);
        this.carId = carId;
    }
}
