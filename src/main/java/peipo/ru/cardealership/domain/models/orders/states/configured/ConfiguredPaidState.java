package peipo.ru.cardealership.domain.models.orders.states.configured;

import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.ConfiguredOrderState;

public class ConfiguredPaidState implements ConfiguredOrderState
{

    @Override
    public void approve(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Already approve. Need deliver");
    }

    @Override
    public void pay(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Already pay. Need deliver");
    }

    @Override
    public void deliver(ConfiguredCarOrder order)
    {
        order.setState(new ConfiguredReadyForPickupState());
    }

    @Override
    public void finish(ConfiguredCarOrder order)
    {
        throw new DomainValidationException("Not ready. Need deliver");
    }

    @Override
    public void cancel(ConfiguredCarOrder order)
    {
        order.setState(new ConfiguredCancelledState());
    }
}
