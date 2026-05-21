package peipo.ru.order.domain.models.orders.states.stocks;

import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.domain.models.orders.states.StockOrderState;

public class StockReadyForPickupState implements StockOrderState
{
    @Override
    public void approve(StockCarOrder order)
    {
        throw new DomainValidationException(
                "Already approved"
        );
    }

    @Override
    public void pay(StockCarOrder order)
    {
        throw new DomainValidationException(
                "Already paid"
        );
    }

    @Override
    public void readyForPickup(StockCarOrder order)
    {
        throw new DomainValidationException(
                "Already ready for pickup"
        );
    }

    @Override
    public void finish(StockCarOrder order)
    {
        order.setState(new StockCompletedState());
    }

    @Override
    public void cancel(StockCarOrder order)
    {
        throw new DomainValidationException(
                "already finished"
        );
    }
}
