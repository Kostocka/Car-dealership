package org.kostocka.cardealership.domain.models.orders.states.configured;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.ConfiguredOrderState;

public class ConfiguredCreatedState implements ConfiguredOrderState
{

    @Override
    public void approve(ConfiguredCarOrder order)
    {
        order.setState(new ConfiguredWarehouseApprovedState());
    }

    @Override
    public void pay(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Order not approved");
    }

    @Override
    public void deliver(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Order not approved");
    }

    @Override
    public void finish(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Order not approved");
    }

    @Override
    public void cancel(ConfiguredCarOrder order)
    {
        order.setState(new ConfiguredCancelledState());
    }
}
