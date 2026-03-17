package org.kostocka.cardealership.domain.repository;

import java.util.List;
import org.kostocka.cardealership.domain.models.orders.StockCarOrder;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import org.kostocka.cardealership.domain.vo.id.OrderId;

public interface StockOrderRepository extends Repository<StockCarOrder, OrderId>
{
    List<StockCarOrder> findByClientId(ClientId clientId);

    List<StockCarOrder> findByManagerId(EmployeeId employeeId);
}