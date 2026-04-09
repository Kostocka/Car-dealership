package peipo.ru.cardealership.infrastructure.web.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.cardealership.application.usecases.orders.CreateStockOrderUseCase;
import peipo.ru.cardealership.application.usecases.orders.GetStockOrdersUseCase;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.orders.StockOrderMapper;
import peipo.ru.cardealership.infrastructure.web.dto.orders.CreateStockOrderRequest;
import peipo.ru.cardealership.infrastructure.web.dto.orders.StockCarOrderDto;

@RestController
@RequestMapping("/orders/stock")
@RequiredArgsConstructor
public class StockOrderController
{
    private final CreateStockOrderUseCase createStockOrderUseCase;
    private final GetStockOrdersUseCase getStockOrdersUseCase;
    private final StockOrderMapper stockOrderMapper;

    @PostMapping
    public StockCarOrderDto createOrder(@RequestBody CreateStockOrderRequest createStockOrderRequest)
    {
        ClientId clientId = new ClientId(createStockOrderRequest.getClientId());
        CarId carId = new CarId(createStockOrderRequest.getCarId());

        StockCarOrder order = createStockOrderUseCase.execute(clientId, carId);
        return stockOrderMapper.toDto(order);
    }

    @GetMapping
    public List<StockCarOrderDto> getOrders()
    {
        return getStockOrdersUseCase.execute().stream()
                .map(stockOrderMapper::toDto).toList();
    }
}

