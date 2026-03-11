package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.models.orders.Order;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

import java.util.List;

public interface CustomOrderRepository extends Repository<Order, OrderId>
{
    List<Order> findByClientId(ClientId clientId);
    List<Order> findByManagerId(EmployeeId employeeId);
}
