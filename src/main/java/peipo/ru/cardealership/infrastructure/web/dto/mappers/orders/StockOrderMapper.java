package peipo.ru.cardealership.infrastructure.web.dto.mappers.orders;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.StockOrderState;
import peipo.ru.cardealership.domain.models.orders.states.stocks.*;
import peipo.ru.cardealership.infrastructure.web.dto.orders.StockCarOrderDto;

@Component
@RequiredArgsConstructor
public class StockOrderMapper
{
    public StockCarOrderDto toDto(StockCarOrder stockCarOrder)
    {
        StockCarOrderDto dto = new StockCarOrderDto();
        dto.setClientId(stockCarOrder.getClientId().id());
        dto.setManagerId(stockCarOrder.getManagerId().id());
        dto.setCarId(stockCarOrder.getCarId().id());
        dto.setState(getStatename(stockCarOrder.getState()));
        return dto;
    }

    private String getStatename(StockOrderState state)
    {
        if (state instanceof StockCreatedState) return "CREATED";
        if (state instanceof StockCancelledState) return "CANCELLED";
        if (state instanceof StockPaidState) return "PAID";
        if (state instanceof StockCompletedState) return  "COMPLETED";
        if (state instanceof StockManagerApprovedState) return  "WAREHOUSE APPROVED";
        return "UNKNOWN";
    }
}

