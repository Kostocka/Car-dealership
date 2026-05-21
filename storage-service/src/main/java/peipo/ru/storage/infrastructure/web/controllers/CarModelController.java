package peipo.ru.storage.infrastructure.web.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.common.dto.cars.CarConfigurationDto;
import peipo.ru.common.dto.cars.CarModelResponceDto;
import peipo.ru.common.dto.cars.CreateCarRequest;
import peipo.ru.common.security.RolesAllowed;
import peipo.ru.storage.application.usecases.models.AddCarModelUseCase;
import peipo.ru.storage.application.usecases.models.GetCarModelsUseCase;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.infrastructure.web.dto.mappers.cars.CarModelDtoMapper;

@RestController
@RequestMapping("/car-models")
@RequiredArgsConstructor
public class CarModelController
{
    private final AddCarModelUseCase addCarModelUseCase;
    private final GetCarModelsUseCase carModelsUseCase;
    private final CarModelDtoMapper carModelDtoMapper;

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping
    public CarModelResponceDto addCarModel(@RequestBody CreateCarRequest createCarRequest)
    {
        CarModel model = carModelDtoMapper.toDomain(createCarRequest.getConfiguration());
        return carModelDtoMapper.toModelDto(addCarModelUseCase.execute(model));
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping()
    public List<CarConfigurationDto> getAllCars()
    {
        return carModelsUseCase.execute().stream()
                .map(carModelDtoMapper::toDto)
                .toList();
    }
}
