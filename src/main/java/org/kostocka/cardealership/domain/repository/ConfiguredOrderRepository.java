package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

import java.util.List;

public interface ConfiguredOrderRepository extends Repository<ConfiguredCarOrder, OrderId>
{
    List<ConfiguredCarOrder> findByClientId(ClientId clientId);
    List<ConfiguredCarOrder> findByManagerId(EmployeeId employeeId);
}
