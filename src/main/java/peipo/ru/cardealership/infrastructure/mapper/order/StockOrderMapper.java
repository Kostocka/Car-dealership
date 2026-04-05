package peipo.ru.cardealership.infrastructure.mapper.order;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.StockOrderState;
import peipo.ru.cardealership.domain.models.orders.states.stocks.*;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;
import peipo.ru.cardealership.infrastructure.mapper.cars.CarMapper;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.StockCarOrderEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.StockOrderStateEnum;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public class StockOrderMapper
{
    public StockCarOrder toDomain(StockCarOrderEntity stockCarOrderEntity)
    {
        return new StockCarOrder(
                new OrderId(stockCarOrderEntity.getOrderId()),
                new ClientId(stockCarOrderEntity.getClientId()),
                new EmployeeId(stockCarOrderEntity.getManagerId()),
                new CarId(stockCarOrderEntity.getCar().getCarId())
        );
    }

    public StockCarOrderEntity toEntity(StockCarOrder stockCarOrder)
    {
        StockCarOrderEntity stockCarOrderEntity = new StockCarOrderEntity();
        stockCarOrderEntity.setOrderState(toStateEnam(stockCarOrder.getState()));
        stockCarOrderEntity.setCar(stockCarOrderEntity.getCar());
        stockCarOrderEntity.setOrderId(stockCarOrderEntity.getOrderId());
        stockCarOrderEntity.setClientId(stockCarOrderEntity.getClientId());
        stockCarOrderEntity.setManagerId(stockCarOrderEntity.getManagerId());
        return stockCarOrderEntity;
    }

    protected StockOrderState toState(StockOrderStateEnum stockOrderStateEnum)
    {
        return switch (stockOrderStateEnum)
        {
            case Created -> new StockCreatedState();
            case Approved -> new StockManagerApprovedState();
            case Cancelled -> new StockCancelledState();
            case Finished -> new StockCompletedState();
            case Paid ->  new StockPaidState();
        };
    }

    protected StockOrderStateEnum toStateEnam(StockOrderState stockOrderState)
    {
        if(stockOrderState instanceof StockCreatedState) return StockOrderStateEnum.Created;
        if (stockOrderState instanceof StockManagerApprovedState) return StockOrderStateEnum.Approved;
        if (stockOrderState instanceof StockCancelledState) return StockOrderStateEnum.Cancelled;
        if (stockOrderState instanceof  StockCancelledState) return StockOrderStateEnum.Cancelled;
        if (stockOrderState instanceof StockPaidState) return StockOrderStateEnum.Paid;

        throw new DomainValidationException("Unknown StockOrderState" + stockOrderState);
    }
}
