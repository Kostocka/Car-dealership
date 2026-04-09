package peipo.ru.cardealership.infrastructure.persistence.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity
{
    @Id
    private UUID id;

    private Instant createdAt;
    private Instant updatedAt;
    private boolean deleted;
}
