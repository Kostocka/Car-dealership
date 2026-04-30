package peipo.ru.storage.application.usecases.models;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.repository.CarRepository;

@Service
@RequiredArgsConstructor
public class AddCarUseCase
{
    private final CarRepository carRepository;

    public Car execute(Car car)
    {
        return carRepository.save(car);
    }
}
