package peipo.ru.cardealership.domain.models.orders.states.stocks;

import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.StockOrderState;

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
