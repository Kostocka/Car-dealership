package peipo.ru.storage.domain.models;

import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.AssemblyStatus;

@Getter
public class StockAssemblyOrder extends AssemblyOrder
{

    private CarId carId;

    public StockAssemblyOrder(UUID id, OrderId sourceOrderId, Instant createdAt, Instant updatedAt,
                              AssemblyStatus status, boolean removed, CarId carId)
    {
        super(id, sourceOrderId, createdAt, updatedAt, status, removed);
        this.carId = carId;
    }
}
