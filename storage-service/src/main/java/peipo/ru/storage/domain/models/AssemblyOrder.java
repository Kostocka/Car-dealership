package peipo.ru.storage.domain.models;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.AssemblyStatus;

@Getter
@AllArgsConstructor
public class AssemblyOrder
{
    private final UUID id;
    private final OrderId sourceOrderId;

    private final Instant createdAt;
    private final Instant updatedAt;

    private AssemblyStatus status;
    private boolean removed;

    public void markAssembled()
    {
        this.status = AssemblyStatus.ASSEMBLED;
    }

    public void markFailed()
    {
        this.status = AssemblyStatus.FAIL;
    }
}