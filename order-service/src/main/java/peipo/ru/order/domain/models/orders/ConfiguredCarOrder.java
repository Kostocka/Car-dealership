package peipo.ru.order.domain.models.orders;

import lombok.Getter;
import lombok.Setter;
import peipo.ru.order.domain.models.CarConfiguration;
import peipo.ru.order.domain.models.orders.states.ConfiguredOrderState;
import peipo.ru.order.domain.models.orders.states.configured.ConfiguredCreatedState;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;

@Getter
public class ConfiguredCarOrder extends Order
{
    private final CarConfiguration configuration;

    @Setter
    private ConfiguredOrderState state;

    public ConfiguredCarOrder(OrderId orderId, ClientId clientId,
                              EmployeeId managerId, CarConfiguration configuration)
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
