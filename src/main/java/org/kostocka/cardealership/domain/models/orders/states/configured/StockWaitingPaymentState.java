package org.kostocka.cardealership.domain.models.orders.states.configured;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.ConfiguredOrderState;

public class StockWaitingPaymentState implements ConfiguredOrderState
{

    @Override
    public void approve(ConfiguredCarOrder order) {
        throw new DomainValidationException("Already approved. Need pay");
    }

    @Override
    public void pay(ConfiguredCarOrder order) {
        order.setState(new ConfiguredPaidState());
    }

    @Override
    public void deliver(ConfiguredCarOrder order) {
        throw new DomainValidationException("Not ready. Need pay");
    }

    @Override
    public void finish(ConfiguredCarOrder order) {
        throw new DomainValidationException("Not ready. Need pay");
    }

    @Override
    public void cancel(ConfiguredCarOrder order) {
        order.setState(new ConfiguredCancelledState());
    }
}
