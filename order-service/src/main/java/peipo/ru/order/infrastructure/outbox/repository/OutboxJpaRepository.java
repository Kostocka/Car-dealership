package peipo.ru.order.infrastructure.outbox.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.order.infrastructure.outbox.entity.OutboxEventEntity;

public interface OutboxJpaRepository extends JpaRepository<OutboxEventEntity, UUID>
{
    List<OutboxEventEntity> findByProcessedFalse();
}
