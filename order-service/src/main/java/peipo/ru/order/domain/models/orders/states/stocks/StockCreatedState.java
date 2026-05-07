package peipo.ru.order.domain.models.orders.states.stocks;

import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.domain.models.orders.states.StockOrderState;

public class StockCreatedState implements StockOrderState
{

    @Override
    public void approve(StockCarOrder order)
    {
        order.setState(new StockManagerApprovedState());
    }

    @Override
    public void pay(StockCarOrder order)
    {
        throw new DomainValidationException("Order not approved");
    }

    @Override
    public void readyForPickup(StockCarOrder order)
    {
        throw new DomainValidationException("Order not approved");
    }

    @Override
    public void finish(StockCarOrder order)
    {
        throw new DomainValidationException("Order not approved");
    }

    @Override
    public void cancel(StockCarOrder order)
    {
        order.setState(new StockCancelledState());
    }
}
