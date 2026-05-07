package peipo.ru.order.infrastructure.persistence.mapper.order;

import org.mapstruct.Mapper;
import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.models.orders.states.ConfiguredOrderState;
import peipo.ru.order.domain.models.orders.states.configured.*;
import peipo.ru.order.infrastructure.persistence.entity.order.ConfiguredCarOrderEntity;
import peipo.ru.order.infrastructure.persistence.entity.order.ConfiguredOrderStateEnum;

@Mapper(componentModel = "spring")
public abstract class ConfiguredOrderMapper
{
    public ConfiguredCarOrderEntity toEntity(ConfiguredCarOrder domain)
    {
        CarConfiguration c = domain.getConfiguration();

        ConfiguredCarOrderEntity e = new ConfiguredCarOrderEntity();

        e.setId(domain.getOrderId().id());
        e.setClientId(domain.getClientId().id());
        e.setManagerId(domain.getManagerId().id());

        e.setBrand(c.getBrand());
        e.setModel(c.getModel());
        e.setBody(c.getBody().id());
        e.setEngine(c.getEngine().id());
        e.setGearBox(c.getGearBox().id());
        e.setInterior(c.getInterior().id());
        e.setWheels(c.getWheels().id());
        e.setDrivetrainType(c.getDrivetrainType());
        e.setColor(c.getColor());

        e.setOrderState(toStateEnum(domain.getState()));

        return e;
    }

    public ConfiguredCarOrder toDomain(ConfiguredCarOrderEntity e)
    {
        CarConfiguration config = CarConfiguration.builder()
                .brand(e.getBrand())
                .model(e.getModel())
                .body(new PartId(e.getBody()))
                .engine(new PartId(e.getEngine()))
                .gearBox(new PartId(e.getGearBox()))
                .interior(new PartId(e.getInterior()))
                .wheels(new PartId(e.getWheels()))
                .drivetrainType(e.getDrivetrainType())
                .color(e.getColor())
                .build();

        ConfiguredCarOrder order =
                new ConfiguredCarOrder(
                        new OrderId(e.getId()),
                        new ClientId(e.getClientId()),
                        new EmployeeId(e.getManagerId()),
                        config
                );

        order.setState(toState(e.getOrderState()));

        return order;
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