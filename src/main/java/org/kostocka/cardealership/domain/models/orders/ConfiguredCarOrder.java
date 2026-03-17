package org.kostocka.cardealership.domain.models.orders;

import lombok.Getter;
import lombok.Setter;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.orders.states.ConfiguredOrderState;
import org.kostocka.cardealership.domain.models.orders.states.configured.ConfiguredCreatedState;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

@Getter
public class ConfiguredCarOrder extends Order
{
    private final CarModel configuration;

    @Setter
    private ConfiguredOrderState state;

    public ConfiguredCarOrder(OrderId orderId, ClientId clientId,
                              EmployeeId managerId, CarModel configuration)
    {
        super(orderId, clientId, managerId);
        this.configuration = configuration;
        this.state = new ConfiguredCreatedState();
    }

    public void approve()
    {
        state.approve(this);
    }

    public void pay()
    {
        state.pay(this);
    }

    public void deliver()
    {
        state.deliver(this);
    }

    public void finish()
    {
        state.finish(this);
    }

    public void cancel()
    {
        state.cancel(this);
    }
}
