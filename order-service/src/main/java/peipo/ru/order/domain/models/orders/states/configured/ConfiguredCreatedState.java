package peipo.ru.order.domain.models.orders.states.configured;

import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.models.orders.states.ConfiguredOrderState;

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
