package peipo.ru.order.domain.models.orders.states.stocks;

import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.domain.models.orders.states.StockOrderState;

public class StockManagerApprovedState implements StockOrderState
{

    @Override
    public void approve(StockCarOrder order)
    {
        throw new DomainValidationException("Already approved");
    }

    @Override
    public void pay(StockCarOrder order)
    {
        order.setState(new StockPaidState());
    }

    @Override
    public void finish(StockCarOrder order)
    {
        throw new DomainValidationException("Not paid");
    }

    @Override
    public void cancel(StockCarOrder order)
    {
        order.setState(new StockCancelledState());
    }
}
