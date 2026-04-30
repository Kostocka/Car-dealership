package peipo.ru.order.infrastructure.persistence.mapper.order;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.order.domain.models.CarModel;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.models.orders.states.ConfiguredOrderState;
import peipo.ru.order.domain.models.orders.states.configured.*;
import peipo.ru.order.domain.vo.id.CarModelId;
import peipo.ru.order.domain.vo.id.ClientId;
import peipo.ru.order.domain.vo.id.EmployeeId;
import peipo.ru.order.domain.vo.id.OrderId;
import peipo.ru.order.infrastructure.persistence.entity.cars.CarConfigurationEmbeddable;
import peipo.ru.order.infrastructure.persistence.entity.order.ConfiguredCarOrderEntity;
import peipo.ru.order.infrastructure.persistence.entity.order.ConfiguredOrderStateEnum;

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
                new CarModelId(emb.getModelId()),
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
        emb.setModelId(domain.getConfiguration().getModelId().id());
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
            case ConfiguredCreatedState configuredCreatedState
                    -> ConfiguredOrderStateEnum.Created;
            case ConfiguredWarehouseApprovedState configuredWarehouseApprovedState
                    -> ConfiguredOrderStateEnum.Approved;
            case ConfiguredCancelledState configuredCancelledState
                    -> ConfiguredOrderStateEnum.Cancelled;
            case ConfiguredPaidState configuredPaidState
                    -> ConfiguredOrderStateEnum.Paid;
            case ConfiguredReadyForPickupState configuredReadyForPickupState
                    -> ConfiguredOrderStateEnum.ReadyForPick;
            case ConfiguredCompletedState configuredCompletedState
                    -> ConfiguredOrderStateEnum.Finished;

            case null, default
                    -> throw new DomainValidationException("Unknown StockOrderState" + configuredOrderState);
        };
    }
}