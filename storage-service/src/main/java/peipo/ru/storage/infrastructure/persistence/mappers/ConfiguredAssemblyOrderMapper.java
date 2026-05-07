package peipo.ru.storage.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.AssemblyOrder;
import peipo.ru.storage.domain.models.ConfiguredAssemblyOrder;
import peipo.ru.storage.infrastructure.persistence.entity.assembly.AssemblyOrderConfigurationEmbeddable;
import peipo.ru.storage.infrastructure.persistence.entity.assembly.ConfiguredAssemblyOrderEntity;

@Component
public class ConfiguredAssemblyOrderMapper
{
    public ConfiguredAssemblyOrder toDomain(ConfiguredAssemblyOrderEntity entity)
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

        return new ConfiguredAssemblyOrder(
                entity.getId(),
                new OrderId(entity.getSourceOrderId()),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getStatus(),
                entity.isRemoved(),
                configuration
        );
    }

    public ConfiguredAssemblyOrderEntity toEntity(ConfiguredAssemblyOrder domain)
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

        ConfiguredAssemblyOrderEntity entity = new ConfiguredAssemblyOrderEntity();

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
