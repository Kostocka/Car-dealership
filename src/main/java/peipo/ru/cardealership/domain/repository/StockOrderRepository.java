package peipo.ru.cardealership.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.vo.id.OrderId;

public interface StockOrderRepository extends JpaRepository<StockCarOrder, OrderId>,
        JpaSpecificationExecutor<StockCarOrder> {}