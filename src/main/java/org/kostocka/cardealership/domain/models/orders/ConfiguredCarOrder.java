package org.kostocka.cardealership.domain.models.orders;

import lombok.Getter;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

@Getter
public class ConfiguredCarOrder extends Order
{
    private final CarModel configuration;

    public ConfiguredCarOrder(OrderId orderId, ClientId clientId, EmployeeId managerId, Money price, OrderState state, CarModel configuration)
    {
        super(orderId, clientId, managerId, price, state);
        this.configuration = configuration;
    }
}
