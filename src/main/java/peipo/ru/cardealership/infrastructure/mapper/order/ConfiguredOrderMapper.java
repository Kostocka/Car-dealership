package peipo.ru.cardealership.infrastructure.mapper.order;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.ConfiguredOrderState;
import peipo.ru.cardealership.domain.models.orders.states.configured.*;
import peipo.ru.cardealership.domain.models.orders.states.stocks.*;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;
import peipo.ru.cardealership.infrastructure.mapper.cars.CarModelMapper;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarModelEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.ConfiguredCarOrderEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.ConfiguredOrderStateEnum;

@Mapper(componentModel = "spring", uses = CarModelMapper.class)
public abstract class ConfiguredOrderMapper
{
    public ConfiguredCarOrder toDomain(ConfiguredCarOrderEntity configuredCarOrderEntity)
    {
        ConfiguredCarOrder order = new ConfiguredCarOrder(
                new OrderId(configuredCarOrderEntity.getOrderId()),
                new ClientId(configuredCarOrderEntity.getClientId()),
                new EmployeeId(configuredCarOrderEntity.getManagerId()),
                map(configuredCarOrderEntity.getConfiguration())
        );

        order.setState(toState(configuredCarOrderEntity.getOrderState()));
        return order;
    }

    public ConfiguredCarOrderEntity toEntity(ConfiguredCarOrder domain)
    {
        ConfiguredCarOrderEntity entity = new ConfiguredCarOrderEntity();
        entity.setOrderId(domain.getOrderId().id());
        entity.setClientId(domain.getClientId().id());
        entity.setManagerId(domain.getManagerId().id());

        entity.setConfiguration(map(domain.getConfiguration()));

        entity.setOrderState(toStateEnam(domain.getState()));
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
            case Paid ->  new ConfiguredPaidState();
            case ReadyForPick -> new ConfiguredReadyForPickupState();
        };
    }

    protected ConfiguredOrderStateEnum toStateEnam(ConfiguredOrderState configuredOrderState)
    {
        if(configuredOrderState instanceof StockCreatedState) return ConfiguredOrderStateEnum.Created;
        if (configuredOrderState instanceof StockManagerApprovedState) return ConfiguredOrderStateEnum.Approved;
        if (configuredOrderState instanceof StockCancelledState) return ConfiguredOrderStateEnum.Cancelled;
        if (configuredOrderState instanceof  StockCancelledState) return ConfiguredOrderStateEnum.Cancelled;
        if (configuredOrderState instanceof StockPaidState) return ConfiguredOrderStateEnum.Paid;

        throw new DomainValidationException("Unknown StockOrderState" + configuredOrderState);
    }

    protected abstract CarModel map(CarModelEntity entity);

    protected abstract CarModelEntity map(CarModel domain);
}