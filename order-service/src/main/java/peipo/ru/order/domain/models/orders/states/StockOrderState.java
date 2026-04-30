package peipo.ru.order.domain.models.orders.states;

import peipo.ru.order.domain.models.orders.StockCarOrder;

public interface StockOrderState
{
    void approve(StockCarOrder order);

    void pay(StockCarOrder order);

    void finish(StockCarOrder order);

    void cancel(StockCarOrder order);
}
