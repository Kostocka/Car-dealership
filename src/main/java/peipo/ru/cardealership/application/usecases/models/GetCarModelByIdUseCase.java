package peipo.ru.cardealership.application.usecases.models;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.exception.EntityNotFoundException;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.repository.CarModelRepository;
import peipo.ru.cardealership.domain.vo.id.CarModelId;

@AllArgsConstructor
public class GetCarModelByIdUseCase
{
    private CarModelRepository carModelRepository;

    public CarModel execute(CarModelId carModelId)
    {
        return carModelRepository.findById(carModelId).orElseThrow(
                () -> new EntityNotFoundException("CarModel with id " + carModelId + " not found")
        );
    }
}
