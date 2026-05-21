package peipo.ru.order.infrastructure.web.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.common.dto.CarFilterDto;
import peipo.ru.common.dto.cars.CarResponseDto;
import peipo.ru.common.grpc.*;
import peipo.ru.common.security.RolesAllowed;
import peipo.ru.order.infrastructure.grpc.CarGrpcClient;
import peipo.ru.order.infrastructure.grpc.CarGrpcMapper;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController
{
    private final CarGrpcClient client;
    private final CarGrpcMapper mapper;

    @GetMapping
    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    public List<CarResponseDto> getAll(CarFilterDto filter)
    {
        return mapper.toDto(client.getAllCars(filter));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"USER", "MANAGER", "ADMIN"})
    public CarResponseDto getById(@PathVariable String id)
    {
        return mapper.toDto(client.getById(id));
    }
}
