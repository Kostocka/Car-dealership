package peipo.ru.cardealership.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.cardealership.application.usecases.orders.CreateConfiguredOrderUseCase;
import peipo.ru.cardealership.application.usecases.orders.GetConfiguredOrdersUseCase;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.cars.CarModelDtoMapper;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.orders.ConfiguredOrderMapper;
import peipo.ru.cardealership.infrastructure.web.dto.orders.ConfiguredCarOrderDto;
import peipo.ru.cardealership.infrastructure.web.dto.orders.CreateConfiguredOrderRequest;

import java.util.List;

@RestController
@RequestMapping("/orders/configured")
@RequiredArgsConstructor
public class ConfiguredOrderController
{
    private final CreateConfiguredOrderUseCase createConfiguredOrderUseCase;
    private final GetConfiguredOrdersUseCase getConfiguredOrdersUseCase;
    private final ConfiguredOrderMapper orderDtoMapper;
    private final CarModelDtoMapper carModelMapper;

    @PostMapping
    public ConfiguredCarOrderDto createOrder(@RequestBody CreateConfiguredOrderRequest createConfiguredOrderRequest)
    {
        ClientId clientId = new ClientId(createConfiguredOrderRequest.getClientId());
        CarModel model = carModelMapper.toDomain(createConfiguredOrderRequest.getConfiguration());

        ConfiguredCarOrder order = createConfiguredOrderUseCase.execute(clientId, model);
        return orderDtoMapper.toDto(order);
    }

    @GetMapping
    public List<ConfiguredCarOrderDto> getOrders()
    {
        return getConfiguredOrdersUseCase.execute().stream()
                .map(orderDtoMapper::toDto).toList();
    }
}
