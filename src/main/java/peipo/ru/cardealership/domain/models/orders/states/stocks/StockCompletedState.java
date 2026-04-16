package peipo.ru.cardealership.domain.models.orders.states.stocks;

import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.StockOrderState;

public class StockCompletedState implements StockOrderState
{

    @Override
    public void approve(StockCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }

    @Override
    public void pay(StockCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }

    @Override
    public void finish(StockCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }

    @Override
    public void cancel(StockCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }
}
