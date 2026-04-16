package peipo.ru.cardealership.application.usecases.models;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.exception.EntityNotFoundException;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.repository.CarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;

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
