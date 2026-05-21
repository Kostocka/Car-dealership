package peipo.ru.order.domain.models.orders.states.configured;

import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.models.orders.states.ConfiguredOrderState;

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
