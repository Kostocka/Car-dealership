package peipo.ru.storage.application.usecases.models;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.CarModelRepository;
import peipo.ru.storage.domain.vo.CarModelId;

@Service
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
