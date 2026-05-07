package peipo.ru.storage.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.StockAssemblyOrder;
import peipo.ru.storage.infrastructure.persistence.entity.assembly.StockAssemblyOrderEntity;

@Component
public class StockAssemblyOrderMapper
{
    public StockAssemblyOrder toDomain(StockAssemblyOrderEntity e)
    {
        return new StockAssemblyOrder(
                e.getId(),
                new OrderId(e.getSourceOrderId()),
                e.getCreatedAt(),
                e.getUpdatedAt(),
                e.getStatus(),
                e.isRemoved(),
                new CarId(e.getCarId())
        );
    }

    public StockAssemblyOrderEntity toEntity(StockAssemblyOrder d)
    {
        StockAssemblyOrderEntity e = new StockAssemblyOrderEntity();

        e.setId(d.getId());
        e.setSourceOrderId(d.getSourceOrderId().id());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        e.setStatus(d.getStatus());
        e.setRemoved(d.isRemoved());

        e.setCarId(d.getCarId().id());

        return e;
    }
}
