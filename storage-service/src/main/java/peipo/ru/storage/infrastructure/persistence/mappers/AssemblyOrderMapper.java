package peipo.ru.storage.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.AssemblyOrder;
import peipo.ru.storage.infrastructure.persistence.entity.assembly.AssemblyOrderConfigurationEmbeddable;
import peipo.ru.storage.infrastructure.persistence.entity.assembly.AssemblyOrderEntity;

@Component
public class AssemblyOrderMapper
{
    public AssemblyOrder toDomain(AssemblyOrderEntity entity)
    {
        CarConfiguration configuration =
                new CarConfiguration(
                        entity.getConfiguration().getBrand(),
                        entity.getConfiguration().getModel(),

                        new PartId(entity.getConfiguration().getBodyId()),
                        new PartId(entity.getConfiguration().getEngineId()),
                        new PartId(entity.getConfiguration().getGearBoxId()),
                        new PartId(entity.getConfiguration().getInteriorId()),
                        new PartId(entity.getConfiguration().getWheelsId()),

                        entity.getConfiguration().getDrivetrainType(),
                        entity.getConfiguration().getColor()
                );

        return new AssemblyOrder(
                entity.getId(),
                new OrderId(entity.getSourceOrderId()),
                configuration,
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getStatus(),
                entity.isRemoved()
        );
    }

    public AssemblyOrderEntity toEntity(AssemblyOrder domain)
    {
        AssemblyOrderConfigurationEmbeddable emb =
                new AssemblyOrderConfigurationEmbeddable();

        emb.setBrand(domain.getConfiguration().getBrand());
        emb.setModel(domain.getConfiguration().getModel());

        emb.setBodyId(domain.getConfiguration().getBody().id());
        emb.setEngineId(domain.getConfiguration().getEngine().id());
        emb.setGearBoxId(domain.getConfiguration().getGearBox().id());
        emb.setInteriorId(domain.getConfiguration().getInterior().id());
        emb.setWheelsId(domain.getConfiguration().getWheels().id());

        emb.setColor(domain.getConfiguration().getColor());
        emb.setDrivetrainType(domain.getConfiguration().getDrivetrainType());

        AssemblyOrderEntity entity = new AssemblyOrderEntity();

        entity.setId(domain.getId());
        entity.setSourceOrderId(domain.getSourceOrderId().id());

        entity.setConfiguration(emb);

        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        entity.setStatus(domain.getStatus());
        entity.setRemoved(domain.isRemoved());

        return entity;
    }
}
