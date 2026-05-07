package peipo.ru.storage.infrastructure.persistence.entity.assembly;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.storage.domain.AssemblyStatus;

@Entity
@Table(name = "stock_assembly_order")
@Getter
@Setter
public class StockAssemblyOrderEntity
{
    @Id
    private UUID id;

    private UUID sourceOrderId;

    @Column(name = "car_id")
    private UUID carId;

    private Instant createdAt;
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private AssemblyStatus status;

    private boolean removed;
}
