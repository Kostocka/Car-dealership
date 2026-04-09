package peipo.ru.cardealership.infrastructure.web.controllers;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.cardealership.application.usecases.models.AddCarModelUseCase;
import peipo.ru.cardealership.application.usecases.models.GetCarModelByIdUseCase;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarModelResponceDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CreateCarRequest;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.cars.CarModelDtoMapper;

@RestController
@RequestMapping("/car-models")
@RequiredArgsConstructor
public class CarModelController
{
    private final AddCarModelUseCase addCarModelUseCase;
    private final GetCarModelByIdUseCase getCarModelByIdUseCase;
    private final CarModelDtoMapper carModelDtoMapper;

    @PostMapping
    public CarModelResponceDto addCarModel(@RequestBody CreateCarRequest createCarRequest)
    {
        CarModel model = carModelDtoMapper.toDomain(createCarRequest.getConfiguration());
        return carModelDtoMapper.toModelDto(addCarModelUseCase.execute(model));
    }

    @GetMapping("/{id}")
    public CarModelResponceDto getCarModelById(@PathVariable UUID id)
    {
        CarModel model =  getCarModelByIdUseCase.execute(new CarModelId(id));
        return carModelDtoMapper.toModelDto(model);
    }
}
