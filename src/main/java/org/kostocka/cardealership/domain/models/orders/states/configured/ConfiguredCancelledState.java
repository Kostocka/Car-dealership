package org.kostocka.cardealership.domain.models.orders.states.configured;

import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.models.orders.states.ConfiguredOrderState;

public class ConfiguredCancelledState implements ConfiguredOrderState
{
    @Override
    public void approve(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }

    @Override
    public void pay(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }

    @Override
    public void deliver(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }

    @Override
    public void finish(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }

    @Override
    public void cancel(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Canceled");
    }
}
