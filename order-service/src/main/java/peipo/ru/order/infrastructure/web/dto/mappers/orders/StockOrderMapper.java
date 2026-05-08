package peipo.ru.order.infrastructure.web.dto.mappers.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.dto.orders.StockCarOrderDto;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.domain.models.orders.states.StockOrderState;
import peipo.ru.order.domain.models.orders.states.stocks.*;

@Component
@RequiredArgsConstructor
public class StockOrderMapper
{
    public StockCarOrderDto toDto(StockCarOrder stockCarOrder)
    {
        StockCarOrderDto dto = new StockCarOrderDto();
        dto.setOrderId(stockCarOrder.getOrderId().id());
        dto.setClientId(stockCarOrder.getClientId().id());
        dto.setManagerId(stockCarOrder.getManagerId().id());
        dto.setCarId(stockCarOrder.getCarId().id());
        dto.setState(getStatename(stockCarOrder.getState()));
        return dto;
    }

    private String getStatename(StockOrderState state)
    {
        return switch (state)
        {
            case StockCreatedState stockCreatedState -> "CREATED";
            case StockCancelledState stockCancelledState -> "CANCELLED";
            case StockPaidState stockPaidState -> "PAID";
            case StockCompletedState stockCompletedState -> "COMPLETED";
            case StockManagerApprovedState stockManagerApprovedState -> "MANAGER APPROVED";
            case StockReadyForPickupState stockReadyForPickupState -> "READY FOR PICKUP";
            case null, default -> "UNKNOWN";
        };
    }
}

