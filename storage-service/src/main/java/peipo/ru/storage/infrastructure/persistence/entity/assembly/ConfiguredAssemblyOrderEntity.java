package peipo.ru.storage.infrastructure.persistence.entity.assembly;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.storage.domain.AssemblyStatus;

@Entity
@Table(name = "configured_assembly_orders")
@Getter
@Setter
public class ConfiguredAssemblyOrderEntity
{
    @Id
    private UUID id;

    private UUID sourceOrderId;

    @Embedded
    private AssemblyOrderConfigurationEmbeddable configuration;

    private Instant createdAt;
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private AssemblyStatus status;

    private boolean removed;
}
