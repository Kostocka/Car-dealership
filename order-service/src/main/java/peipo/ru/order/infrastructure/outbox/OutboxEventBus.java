package peipo.ru.order.infrastructure.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.contracts.events.EventBus;
import peipo.ru.order.infrastructure.outbox.entity.OutboxEventEntity;
import peipo.ru.order.infrastructure.outbox.repository.OutboxJpaRepository;
import peipo.ru.order.infrastructure.outbox.serializer.EventSerializer;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OutboxEventBus implements EventBus
{
    private final OutboxJpaRepository jpa;
    private final EventSerializer serializer;

    @Override
    public void publish(DomainEvent event)
    {
        OutboxEventEntity e = new OutboxEventEntity();

        e.setId(UUID.randomUUID());
        e.setAggregateType(event.getClass().getSimpleName());
        e.setAggregateId(event.aggregateId());
        e.setEventType(event.eventType());
        e.setPayload(serializer.toJson(event));
        e.setCreatedAt(Instant.now());
        e.setProcessed(false);

        jpa.save(e);
    }
}
