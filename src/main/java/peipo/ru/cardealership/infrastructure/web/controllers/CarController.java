package peipo.ru.cardealership.infrastructure.web.controllers;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.cardealership.application.usecases.models.AddCarUseCase;
import peipo.ru.cardealership.application.usecases.models.GetCarByIdUseCase;
import peipo.ru.cardealership.application.usecases.models.GetCarsUseCase;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.infrastructure.persistence.mapper.FilterMapper;
import peipo.ru.cardealership.infrastructure.persistence.mapper.cars.CarMapper;
import peipo.ru.cardealership.infrastructure.security.RolesAllowed;
import peipo.ru.cardealership.infrastructure.web.dto.CarFilterDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarResponseDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CreateCarRequest;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.cars.CarDtoMapper;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.cars.CarFilterMapper;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController
{
    private final AddCarUseCase addCarUseCase;
    private final GetCarByIdUseCase getCarByIdUseCase;
    private final GetCarsUseCase getCarsUseCase;

    private final CarDtoMapper carDtoMapper;
    private final CarMapper carMapper;
    private final FilterMapper filterMapper;
    private final CarFilterMapper carFilterMapper;

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping
    public CarResponseDto addCar(@RequestBody CreateCarRequest createCarRequest)
    {
        Car carDomain = carDtoMapper.toDomain(createCarRequest);
        return carDtoMapper.toDto(addCarUseCase.execute(carDomain));
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/{id}")
    public CarResponseDto getCar(@PathVariable UUID id)
    {
        Car car = getCarByIdUseCase.execute(new CarId(id));
        return carDtoMapper.toDto(car);
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/filter")
    public List<CarResponseDto> getAllCars(CarFilterDto carFilterDto)
    {
        Filter<CarModel> filter = carFilterMapper.toDomain(carFilterDto);

        return getCarsUseCase.execute(filter).stream()
                .map(carDtoMapper::toDto)
                .toList();
    }
}
