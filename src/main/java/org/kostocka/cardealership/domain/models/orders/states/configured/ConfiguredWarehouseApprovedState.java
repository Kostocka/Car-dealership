package org.kostocka.cardealership.domain.models.orders.states.configured;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.ConfiguredOrderState;

public class ConfiguredWarehouseApprovedState implements ConfiguredOrderState
{

    @Override
    public void approve(ConfiguredCarOrder order) {
        throw new DomainValidationException("Already approved");
    }

    @Override
    public void pay(ConfiguredCarOrder order) {
        order.setState(new StockWaitingPaymentState());
    }

    @Override
    public void deliver(ConfiguredCarOrder order) {
        throw new DomainValidationException("Not paid");
    }

    @Override
    public void finish(ConfiguredCarOrder order) {
        throw new DomainValidationException("Not paid");
    }

    @Override
    public void cancel(ConfiguredCarOrder order) {
        order.setState(new ConfiguredCancelledState());
    }
}
