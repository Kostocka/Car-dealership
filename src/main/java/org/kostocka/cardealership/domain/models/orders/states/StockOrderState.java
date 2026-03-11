package org.kostocka.cardealership.domain.models.orders.states;

import org.kostocka.cardealership.domain.models.orders.StockCarOrder;

public interface StockOrderState
{
    void approve(StockCarOrder order);
    void pay(StockCarOrder order);
    void finish(StockCarOrder order);
    void cancel(StockCarOrder order);
}
