package peipo.ru.cardealership.domain.models.orders.states;

import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;

public interface ConfiguredOrderState
{
    void approve(ConfiguredCarOrder order);

    void pay(ConfiguredCarOrder order);

    void deliver(ConfiguredCarOrder order);

    void finish(ConfiguredCarOrder order);

    void cancel(ConfiguredCarOrder order);
}
