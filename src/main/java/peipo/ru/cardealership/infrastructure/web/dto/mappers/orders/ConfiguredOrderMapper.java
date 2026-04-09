package peipo.ru.cardealership.infrastructure.web.dto.mappers.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.models.orders.states.ConfiguredOrderState;
import peipo.ru.cardealership.domain.models.orders.states.configured.*;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.cars.CarModelDtoMapper;
import peipo.ru.cardealership.infrastructure.web.dto.orders.ConfiguredCarOrderDto;

@Component
@RequiredArgsConstructor
public class ConfiguredOrderMapper
{
    private final CarModelDtoMapper carModelMapper;

    public ConfiguredCarOrderDto toDto(ConfiguredCarOrder configuredCarOrder)
    {
        ConfiguredCarOrderDto dto = new ConfiguredCarOrderDto();
        dto.setOrderId(configuredCarOrder.getOrderId().id());
        dto.setClientId(configuredCarOrder.getClientId().id());
        dto.setManagerId(configuredCarOrder.getManagerId().id());
        dto.setConfiguration(carModelMapper.toDto(configuredCarOrder.getConfiguration()));
        dto.setState(getStatename(configuredCarOrder.getState()));
        return dto;
    }

    private String getStatename(ConfiguredOrderState state)
    {
        return switch (state)
        {
            case ConfiguredCreatedState configuredCreatedState -> "CREATED";
            case ConfiguredCancelledState configuredCancelledState -> "CANCELLED";
            case ConfiguredPaidState configuredPaidState -> "PAID";
            case ConfiguredCompletedState configuredCompletedState -> "COMPLETED";
            case ConfiguredWarehouseApprovedState configuredWarehouseApprovedState -> "WAREHOUSE APPROVED";
            case null, default -> "UNKNOWN";
        };
    }
}
