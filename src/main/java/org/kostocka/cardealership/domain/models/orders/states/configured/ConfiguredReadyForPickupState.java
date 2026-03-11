package org.kostocka.cardealership.domain.models.orders.states.configured;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.ConfiguredOrderState;

public class ConfiguredReadyForPickupState implements ConfiguredOrderState
{
    @Override
    public void approve(ConfiguredCarOrder order) {
        throw new DomainValidationException("Already approve.Need puck up");
    }

    @Override
    public void pay(ConfiguredCarOrder order) {
        throw new DomainValidationException("Already pay.Need puck up");
    }

    @Override
    public void deliver(ConfiguredCarOrder order) {
        throw new DomainValidationException("Already delivered");
    }

    @Override
    public void finish(ConfiguredCarOrder order) {
        order.setState(new ConfiguredReadyForPickupState());
    }

    @Override
    public void cancel(ConfiguredCarOrder order) {
        order.setState(new ConfiguredCancelledState());
    }
}
