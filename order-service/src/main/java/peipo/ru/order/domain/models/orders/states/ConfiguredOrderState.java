package peipo.ru.order.domain.models.orders.states;

import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;

public interface ConfiguredOrderState
{
    void approve(ConfiguredCarOrder order);

    void pay(ConfiguredCarOrder order);

    void deliver(ConfiguredCarOrder order);

    void finish(ConfiguredCarOrder order);

    void cancel(ConfiguredCarOrder order);
}
