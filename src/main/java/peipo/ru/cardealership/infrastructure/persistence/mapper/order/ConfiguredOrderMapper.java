package peipo.ru.cardealership.infrastructure.persistence.mapper.order;

import java.awt.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.ConfiguredOrderState;
import peipo.ru.cardealership.domain.models.orders.states.configured.*;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarConfigurationEmbeddable;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.ConfiguredCarOrderEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.ConfiguredOrderStateEnum;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.*;
import peipo.ru.cardealership.infrastructure.persistence.mapper.parts.*;

@Mapper(componentModel = "spring")
public abstract class ConfiguredOrderMapper
{
    @Autowired
    protected EngineJpaRepository engineJpaRepository;

    @Autowired
    protected BodyJpaRepository bodyJpaRepository;

    @Autowired
    protected GearBoxJpaRepository gearBoxJpaRepository;

    @Autowired
    protected InteriorJpaRepository interiorJpaRepository;

    @Autowired
    protected WheelsJpaRepository wheelsJpaRepository;

    @Autowired
    protected EngineMapper engineMapper;

    @Autowired
    protected BodyMapper bodyMapper;

    @Autowired
    protected GearBoxMapper gearBoxMapper;

    @Autowired
    protected InteriorMapper interiorMapper;

    @Autowired
    protected WheelsMapper wheelsMapper;

    public ConfiguredCarOrder toDomain(ConfiguredCarOrderEntity configuredCarOrderEntity)
    {
        CarConfigurationEmbeddable emb = configuredCarOrderEntity.getConfiguration();

        CarModel model = new CarModel(
                null,
                emb.getBrand(),
                emb.getModel(),
                bodyMapper.toDomain(emb.getBody()),
                engineMapper.toDomain(emb.getEngine()),
                gearBoxMapper.toDomain(emb.getGearBox()),
                interiorMapper.toDomain(emb.getInterior()),
                wheelsMapper.toDomain(emb.getWheels()),
                emb.getDrivetrainType(),
                emb.getColor()
        );

        var order = new ConfiguredCarOrder(
                new OrderId(configuredCarOrderEntity.getId()),
                new ClientId(configuredCarOrderEntity.getClientId()),
                new EmployeeId(configuredCarOrderEntity.getManagerId()),
                model
        );
        order.setState(toState(configuredCarOrderEntity.getOrderState()));

        return order;
    }

    public ConfiguredCarOrderEntity toEntity(ConfiguredCarOrder domain)
    {
        ConfiguredCarOrderEntity entity = new ConfiguredCarOrderEntity();
        entity.setId(domain.getOrderId().id());
        entity.setClientId(domain.getClientId().id());
        entity.setManagerId(domain.getManagerId().id());
        entity.setOrderState(toStateEnum(domain.getState()));

        CarConfigurationEmbeddable emb = new CarConfigurationEmbeddable();
        emb.setBrand(domain.getConfiguration().getBrand());
        emb.setModel(domain.getConfiguration().getModel());
        emb.setBody(bodyMapper.toEntity(domain.getConfiguration().getBody()));
        emb.setEngine(engineMapper.toEntity(domain.getConfiguration().getEngine()));
        emb.setGearBox(gearBoxMapper.toEntity(domain.getConfiguration().getGearBox()));
        emb.setInterior(interiorMapper.toEntity(domain.getConfiguration().getInterior()));
        emb.setWheels(wheelsMapper.toEntity(domain.getConfiguration().getWheels()));
        emb.setDrivetrainType(domain.getConfiguration().getDrivetrainType());
        emb.setColor(domain.getConfiguration().getColor().toString());

        entity.setConfiguration(emb);

        return entity;
    }

    protected ConfiguredOrderState toState(ConfiguredOrderStateEnum configuredOrderState)
    {
        return switch (configuredOrderState)
        {
            case Created -> new ConfiguredCreatedState();
            case Approved -> new ConfiguredWarehouseApprovedState();
            case Cancelled -> new ConfiguredCancelledState();
            case Finished -> new ConfiguredCompletedState();
            case Paid -> new ConfiguredPaidState();
            case ReadyForPick -> new ConfiguredReadyForPickupState();
        };
    }

    protected ConfiguredOrderStateEnum toStateEnum(ConfiguredOrderState configuredOrderState)
    {
        return switch (configuredOrderState)
        {
            case ConfiguredCreatedState configuredCreatedState -> ConfiguredOrderStateEnum.Created;
            case ConfiguredWarehouseApprovedState configuredWarehouseApprovedState -> ConfiguredOrderStateEnum.Approved;
            case ConfiguredCancelledState configuredCancelledState -> ConfiguredOrderStateEnum.Cancelled;
            case ConfiguredPaidState configuredPaidState -> ConfiguredOrderStateEnum.Paid;
            case ConfiguredReadyForPickupState configuredReadyForPickupState -> ConfiguredOrderStateEnum.ReadyForPick;

            case null, default -> throw new DomainValidationException("Unknown StockOrderState" + configuredOrderState);
        };
    }
}