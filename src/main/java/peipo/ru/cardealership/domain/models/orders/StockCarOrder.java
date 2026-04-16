package peipo.ru.cardealership.domain.models.orders;

import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.domain.models.orders.states.StockOrderState;
import peipo.ru.cardealership.domain.models.orders.states.stocks.StockCreatedState;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;

@Getter
public class StockCarOrder extends Order
{
    private final CarId carId;
    @Setter
    private StockOrderState state;

    public StockCarOrder(OrderId orderId, ClientId clientId, EmployeeId managerId, CarId carId)
    {
        super(orderId, clientId, managerId);
        this.carId = carId;
        this.state = new StockCreatedState();
    }

    public void approve()
    {
        state.approve(this);
    }

    public void pay()
    {
        state.pay(this);
    }

    public void finish()
    {
        state.finish(this);
    }

    public void cancel()
    {
        state.cancel(this);
    }
}
