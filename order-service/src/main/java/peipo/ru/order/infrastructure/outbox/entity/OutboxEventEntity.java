package peipo.ru.order.infrastructure.outbox.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
public class OutboxEventEntity
{
    @Id
    private UUID id;

    private String aggregateType;

    private UUID aggregateId;

    private String eventType;

    @Lob
    private String payload;

    private Instant createdAt;

    private boolean processed;
}
