package org.kostocka.cardealership.domain.models.orders.states.stocks;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.StockCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.StockOrderState;

public class StockPaidState implements StockOrderState
{

    @Override
    public void approve(StockCarOrder order)
    {
        throw new DomainValidationException("Already approved");
    }

    @Override
    public void pay(StockCarOrder order)
    {
        throw new DomainValidationException("Already paid");
    }

    @Override
    public void finish(StockCarOrder order)
    {
        order.setState(new StockCompletedState());
    }

    @Override
    public void cancel(StockCarOrder order)
    {
        order.setState(new StockCancelledState());
    }
}
