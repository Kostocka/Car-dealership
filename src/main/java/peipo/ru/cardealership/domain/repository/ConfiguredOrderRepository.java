package peipo.ru.cardealership.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.vo.id.OrderId;

public interface ConfiguredOrderRepository extends JpaRepository<ConfiguredCarOrder, OrderId>,
        JpaSpecificationExecutor<ConfiguredCarOrder> {}