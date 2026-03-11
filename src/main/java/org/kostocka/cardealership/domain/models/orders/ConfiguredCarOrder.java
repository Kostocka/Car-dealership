package org.kostocka.cardealership.domain.models.orders;

import lombok.Getter;
import lombok.Setter;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.orders.states.ConfiguredOrderState;
import org.kostocka.cardealership.domain.models.orders.states.configured.ConfiguredCreatedState;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

@Getter
public class ConfiguredCarOrder extends Order
{
    private final CarModel configuration;

    @Setter
    private ConfiguredOrderState state;

    public ConfiguredCarOrder(OrderId orderId, ClientId clientId, EmployeeId managerId, Money price, CarModel configuration)
    {
        super(orderId, clientId, managerId, price);
        this.configuration = configuration;
        this.state = new ConfiguredCreatedState();
    }
}
