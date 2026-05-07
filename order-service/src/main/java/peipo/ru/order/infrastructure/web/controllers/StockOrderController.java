package peipo.ru.order.infrastructure.web.controllers;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.common.security.RolesAllowed;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.order.domain.models.orders.StockCarOrder;
import peipo.ru.order.application.services.StockOrderService;
import peipo.ru.order.infrastructure.web.dto.mappers.orders.StockOrderMapper;
import peipo.ru.order.infrastructure.web.dto.orders.CreateStockOrderRequest;
import peipo.ru.order.infrastructure.web.dto.orders.StockCarOrderDto;

@RestController
@RequestMapping("/orders/stock")
@RequiredArgsConstructor
public class StockOrderController
{
    private final StockOrderMapper stockOrderMapper;
    private final StockOrderService stockOrderService;

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping
    public StockCarOrderDto createOrder(@RequestBody CreateStockOrderRequest createStockOrderRequest,
                                        @AuthenticationPrincipal Jwt jwt)
    {
        ClientId clientId = new ClientId(UUID.fromString(jwt.getSubject()));
        CarId carId = new CarId(createStockOrderRequest.getCarId());

        StockCarOrder order = stockOrderService.createStockOrder(clientId, carId);
        return stockOrderMapper.toDto(order);
    }

    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    @GetMapping("/{orderId}")
    public StockCarOrderDto getOrder(@PathVariable UUID orderId,
                                     @AuthenticationPrincipal Jwt jwt,
                                     Authentication authentication)
    {
        StockCarOrder order = stockOrderService.getStockCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            UUID clientUuid = UUID.fromString(jwt.getSubject());
            if (!order.getClientId().id().equals(clientUuid))
            {
                throw new SecurityException("You are not the owner of this order");
            }
        }

        return stockOrderMapper.toDto(order);
    }

    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    @GetMapping
    public List<StockCarOrderDto> getOrders(@AuthenticationPrincipal Jwt jwt, Authentication authentication)
    {
        List<StockCarOrder> orders;

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            ClientId clientId = new ClientId(UUID.fromString(jwt.getSubject()));
            orders = stockOrderService.getStockCarOrderByClient(clientId);
        } else
        {
            orders = stockOrderService.getAllStockCarOrders();
        }

        return orders.stream().map(stockOrderMapper::toDto).toList();
    }

    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    @PostMapping("/{orderId}/cancel")
    public StockCarOrderDto cancelOrder(@PathVariable UUID orderId,
                                        @AuthenticationPrincipal Jwt jwt,
                                        Authentication authentication)
    {
        StockCarOrder order = stockOrderService.getStockCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            UUID clientUuid = UUID.fromString(jwt.getSubject());
            if (!order.getClientId().id().equals(clientUuid))
            {
                throw new SecurityException("You are not the owner of this order");
            }
        }

        stockOrderService.cancelOrder(order);
        return stockOrderMapper.toDto(order);
    }

    @RolesAllowed({"MANAGER", "ADMIN"})
    @PostMapping("/{orderId}/approve")
    public StockCarOrderDto approveOrder(@PathVariable UUID orderId)
    {
        StockCarOrder order = stockOrderService.getStockCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        stockOrderService.approveOrder(order);
        return stockOrderMapper.toDto(order);
    }

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping("/{orderId}/pay")
    public StockCarOrderDto payOrder(@PathVariable UUID orderId)
    {
        StockCarOrder order = stockOrderService.getStockCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        stockOrderService.payOrder(order);
        return stockOrderMapper.toDto(order);
    }

    @RolesAllowed({"MANAGER", "ADMIN"})
    @PostMapping("/{orderId}/finish")
    public StockCarOrderDto finishOrder(@PathVariable UUID orderId)
    {
        StockCarOrder order = stockOrderService.getStockCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        stockOrderService.finishOrder(order);
        return stockOrderMapper.toDto(order);
    }
}

