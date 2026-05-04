package peipo.ru.order.infrastructure.web.controllers;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.order.domain.models.CarModel;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.services.ConfiguredOrderService;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.common.exception.security.RolesAllowed;
import peipo.ru.order.infrastructure.web.dto.mappers.cars.CarModelDtoMapper;
import peipo.ru.order.infrastructure.web.dto.mappers.orders.ConfiguredOrderMapper;
import peipo.ru.order.infrastructure.web.dto.orders.ConfiguredCarOrderDto;
import peipo.ru.order.infrastructure.web.dto.orders.CreateConfiguredOrderRequest;

@RestController
@RequestMapping("/orders/configured")
@RequiredArgsConstructor
public class ConfiguredOrderController
{
    private final ConfiguredOrderMapper orderDtoMapper;
    private final CarModelDtoMapper carModelMapper;
    private final ConfiguredOrderService configuredOrderService;

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping
    public ConfiguredCarOrderDto createOrder(
            @RequestBody CreateConfiguredOrderRequest createConfiguredOrderRequest,
            @AuthenticationPrincipal Jwt jwt)
    {
        ClientId clientId = new ClientId(UUID.fromString(jwt.getSubject()));
        CarModel model = carModelMapper.toDomain(createConfiguredOrderRequest.getConfiguration());

        ConfiguredCarOrder order = configuredOrderService.createConfiguredCarOrder(clientId, model);
        return orderDtoMapper.toDto(order);
    }

    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    @GetMapping("/{orderId}")
    public ConfiguredCarOrderDto getOrder(@PathVariable UUID orderId,
                                          @AuthenticationPrincipal Jwt jwt,
                                          Authentication authentication)
    {
        ConfiguredCarOrder order = configuredOrderService.getConfiguredCarOrderById(new OrderId(orderId))
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
        return orderDtoMapper.toDto(order);
    }

    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    @GetMapping
    public List<ConfiguredCarOrderDto> getOrders(@AuthenticationPrincipal Jwt jwt,
                                                 Authentication authentication)
    {
        List<ConfiguredCarOrder> orders;
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            ClientId clientId = new ClientId(UUID.fromString(jwt.getSubject()));
            orders = configuredOrderService.getConfiguredCarOrderByClient(clientId);
        } else
        {
            orders = configuredOrderService.getAllConfiguredCarOrders();
        }
        return orders.stream().map(orderDtoMapper::toDto).toList();
    }

    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    @PostMapping("/{orderId}/cancel")
    public ConfiguredCarOrderDto cancelOrder(@PathVariable UUID orderId,
                                             @AuthenticationPrincipal Jwt jwt,
                                             Authentication authentication)
    {
        ConfiguredCarOrder order = configuredOrderService.getConfiguredCarOrderById(new OrderId(orderId))
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

        configuredOrderService.cancelOrder(order);
        return orderDtoMapper.toDto(order);
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/{orderId}/approve")
    public ConfiguredCarOrderDto approveOrder(@PathVariable UUID orderId)
    {
        ConfiguredCarOrder order = configuredOrderService.getConfiguredCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        configuredOrderService.approveOrder(order);
        return orderDtoMapper.toDto(order);
    }

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping("/{orderId}/pay")
    public ConfiguredCarOrderDto payOrder(@PathVariable UUID orderId,
                                          @AuthenticationPrincipal Jwt jwt,
                                          Authentication authentication)
    {
        ConfiguredCarOrder order = configuredOrderService.getConfiguredCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            UUID clientUuid = UUID.fromString(jwt.getSubject());
            if (!order.getClientId().id().equals(clientUuid))
            {
                throw new SecurityException("You are not the owner of this order");
            }
        }
        configuredOrderService.payOrder(order);
        return orderDtoMapper.toDto(order);
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/{orderId}/deliver")
    public ConfiguredCarOrderDto deliverOrder(@PathVariable UUID orderId)
    {
        ConfiguredCarOrder order = configuredOrderService.getConfiguredCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        configuredOrderService.deliverOrder(order);
        return orderDtoMapper.toDto(order);
    }

    @RolesAllowed({"MANAGER", "ADMIN"})
    @PostMapping("/{orderId}/finish")
    public ConfiguredCarOrderDto finishOrder(@PathVariable UUID orderId)
    {
        ConfiguredCarOrder order = configuredOrderService.getConfiguredCarOrderById(new OrderId(orderId))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        configuredOrderService.finishOrder(order);
        return orderDtoMapper.toDto(order);
    }
}
