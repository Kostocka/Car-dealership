package org.kostocka.cardealership.domain.models.orders;

public interface OrderState
{
    void approve(Order order);
    void pay(Order order);
    void deliver(Order order);
    void finish(Order order);
    void cancel(Order order);
}
