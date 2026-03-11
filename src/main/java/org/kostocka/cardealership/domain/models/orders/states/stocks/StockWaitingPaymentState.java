package org.kostocka.cardealership.domain.models.orders.states.stocks;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.StockCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.StockOrderState;

public class StockWaitingPaymentState implements StockOrderState
{

    @Override
    public void approve(StockCarOrder order) {
        throw new DomainValidationException("Already approved. Need pay");
    }

    @Override
    public void pay(StockCarOrder order) {
        order.setState( new StockPaidState());
    }

    @Override
    public void finish(StockCarOrder order) {
        throw new DomainValidationException("Not ready. Need pay");
    }

    @Override
    public void cancel(StockCarOrder order) {
        order.setState(new StockCancelledState());
    }
}
