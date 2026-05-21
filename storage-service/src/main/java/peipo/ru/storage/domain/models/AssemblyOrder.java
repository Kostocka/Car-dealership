package peipo.ru.storage.domain.models;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.AssemblyStatus;

@Getter
@AllArgsConstructor
public abstract class AssemblyOrder
{
    private final UUID id;
    private final OrderId sourceOrderId;

    private final Instant createdAt;
    private Instant updatedAt;

    private AssemblyStatus status;
    private boolean removed;

    public void markAssembled()
    {
        status = AssemblyStatus.ASSEMBLED;
        updatedAt = Instant.now();
    }

    public void markDelivering()
    {
        status = AssemblyStatus.DELIVERING;
        updatedAt = Instant.now();
    }

    public void markDelivered()
    {
        status = AssemblyStatus.DELIVERED;
        updatedAt = Instant.now();
    }

    public void markCancelled()
    {
        status = AssemblyStatus.CANCELLED;
        updatedAt = Instant.now();
    }

    public void markFailed()
    {
        status = AssemblyStatus.FAIL;
        updatedAt = Instant.now();
    }
}