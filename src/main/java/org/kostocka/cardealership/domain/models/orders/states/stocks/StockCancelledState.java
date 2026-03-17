package org.kostocka.cardealership.domain.models.orders.states.stocks;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.StockCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.StockOrderState;

public class StockCancelledState implements StockOrderState
{

    @Override
    public void approve(StockCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }

    @Override
    public void pay(StockCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }

    @Override
    public void finish(StockCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }

    @Override
    public void cancel(StockCarOrder order)
    {
        throw new DomainValidationException("Already Canceled");
    }
}
