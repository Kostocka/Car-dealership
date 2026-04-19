package peipo.ru.cardealership.infrastructure.persistence.mapper.order;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.StockOrderState;
import peipo.ru.cardealership.domain.models.orders.states.stocks.*;
import peipo.ru.cardealership.domain.repository.CarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.OrderId;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.StockCarOrderEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.order.StockOrderStateEnum;
import peipo.ru.cardealership.infrastructure.persistence.mapper.cars.CarMapper;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public class StockOrderMapper
{
    private CarRepository carRepository;

    public StockCarOrder toDomain(StockCarOrderEntity stockCarOrderEntity)
    {
        StockCarOrder order = new StockCarOrder(
                new OrderId(stockCarOrderEntity.getId()),
                new ClientId(stockCarOrderEntity.getClientId()),
                new EmployeeId(stockCarOrderEntity.getManagerId()),
                new CarId(stockCarOrderEntity.getCarId())
        );

        order.setState(toState(stockCarOrderEntity.getOrderState()));
        return order;
    }

    public StockCarOrderEntity toEntity(StockCarOrder stockCarOrder)
    {
        StockCarOrderEntity stockCarOrderEntity = new StockCarOrderEntity();
        stockCarOrderEntity.setOrderState(toStateEnam(stockCarOrder.getState()));
        stockCarOrderEntity.setCarId(stockCarOrder.getCarId().id());
        stockCarOrderEntity.setId(stockCarOrder.getOrderId().id());
        stockCarOrderEntity.setClientId(stockCarOrder.getClientId().id());
        stockCarOrderEntity.setManagerId(stockCarOrder.getManagerId().id());
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
        return switch (stockOrderState)
        {
            case StockCreatedState stockCreatedState -> StockOrderStateEnum.Created;
            case StockManagerApprovedState stockManagerApprovedState -> StockOrderStateEnum.Approved;
            case StockCancelledState stockCancelledState -> StockOrderStateEnum.Cancelled;
            case StockPaidState stockPaidState -> StockOrderStateEnum.Paid;
            case StockCompletedState stockCompletedState -> StockOrderStateEnum.Finished;
            case null, default -> throw new DomainValidationException("Unknown StockOrderState" + stockOrderState);
        };

    }
}
