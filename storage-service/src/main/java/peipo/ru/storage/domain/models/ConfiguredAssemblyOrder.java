package peipo.ru.storage.domain.models;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.AssemblyStatus;

@Getter
public class ConfiguredAssemblyOrder extends AssemblyOrder
{
    private final CarConfiguration configuration;

    public ConfiguredAssemblyOrder(UUID id, OrderId sourceOrderId, Instant createdAt, Instant updatedAt,
                                   AssemblyStatus status, boolean removed, CarConfiguration configuration)
    {
        super(id, sourceOrderId, createdAt, updatedAt, status, removed);
        this.configuration = configuration;
    }
}
