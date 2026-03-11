package org.kostocka.cardealership.domain.models.orders;

import lombok.Getter;
import lombok.Setter;
import org.kostocka.cardealership.domain.models.orders.states.StockOrderState;
import org.kostocka.cardealership.domain.models.orders.states.stocks.StockCreatedState;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.CarId;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

@Getter
public class StockCarOrder extends Order
{
    private final CarId carId;
    @Setter
    private StockOrderState state;

    public StockCarOrder(OrderId orderId, ClientId clientId, EmployeeId managerId, Money price, CarId carId)
    {
        super(orderId, clientId, managerId, price);
        this.carId = carId;
        this.state = new StockCreatedState();
    }
}
