package peipo.ru.cardealership.domain.models.orders.states.configured;

import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.ConfiguredOrderState;

public class ConfiguredCompletedState implements ConfiguredOrderState
{
    @Override
    public void approve(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }

    @Override
    public void pay(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }

    @Override
    public void deliver(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }

    @Override
    public void finish(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }

    @Override
    public void cancel(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Already Completed");
    }
}
