package peipo.ru.cardealership.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peipo.ru.cardealership.application.usecases.models.AddCarUseCase;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarResponseDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CreateCarRequest;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.CarDtoMapper;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController
{
    private final AddCarUseCase addCarUseCase;
    private final CarDtoMapper carDtoMapper;

    @PostMapping
    public CarResponseDto addCar(@RequestBody CreateCarRequest createCarRequest)
    {
        Car carDomain = carDtoMapper.toDomain(createCarRequest);
        addCarUseCase.execute(carDomain);
        return carDtoMapper.toDto(carDomain);
    }
}
