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
        dto.setClientId(configuredCarOrder.getClientId().id());
        dto.setManagerId(configuredCarOrder.getManagerId().id());
        dto.setConfiguration(carModelMapper.toDto(configuredCarOrder.getConfiguration()));
        dto.setState(getStatename(configuredCarOrder.getState()));
        return dto;
    }

    private String getStatename(ConfiguredOrderState state)
    {
        if (state instanceof ConfiguredCreatedState) return "CREATED";
        if (state instanceof ConfiguredCancelledState) return "CANCELLED";
        if (state instanceof ConfiguredPaidState) return "PAID";
        if (state instanceof ConfiguredCompletedState) return  "COMPLETED";
        if (state instanceof ConfiguredWarehouseApprovedState) return  "WAREHOUSE APPROVED";
        return "UNKNOWN";
    }
}
