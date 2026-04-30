package peipo.ru.storage.application.usecases.models;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.repository.CarRepository;
import peipo.ru.common.vo.id.CarId;

@Service
@AllArgsConstructor
public class GetCarByIdUseCase
{
    private CarRepository carRepository;

    public Car execute(CarId carId)
    {
        return carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id " + carId + " not found")
        );
    }
}
