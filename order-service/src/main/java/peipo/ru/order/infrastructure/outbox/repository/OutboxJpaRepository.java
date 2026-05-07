package peipo.ru.order.infrastructure.outbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.order.infrastructure.outbox.entity.OutboxEventEntity;

import java.util.List;
import java.util.UUID;

public interface OutboxJpaRepository extends JpaRepository<OutboxEventEntity, UUID>
{
    List<OutboxEventEntity> findByProcessedFalse();
}
