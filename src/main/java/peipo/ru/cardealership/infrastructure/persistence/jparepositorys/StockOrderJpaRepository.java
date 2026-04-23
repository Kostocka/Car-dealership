package peipo.ru.cardealership.infrastructure.persistence.jparepositorys;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.StockCarOrderEntity;

public interface StockOrderJpaRepository extends JpaRepository<StockCarOrderEntity, UUID>
{
    List<StockCarOrderEntity> findByClientId(UUID clientId);
}