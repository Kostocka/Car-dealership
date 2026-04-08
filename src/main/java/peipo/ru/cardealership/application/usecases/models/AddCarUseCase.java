package peipo.ru.cardealership.application.usecases.models;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.repository.CarRepository;

@Service
@RequiredArgsConstructor
public class AddCarUseCase
{
    private final CarRepository carRepository;

    public void execute(Car car)
    {
        carRepository.save(car);
    }
}
