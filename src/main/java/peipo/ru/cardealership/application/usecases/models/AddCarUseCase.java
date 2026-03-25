package peipo.ru.cardealership.application.usecases.models;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.repository.CarRepository;

@AllArgsConstructor
public class AddCarUseCase
{
    private CarRepository  carRepository;

    public void execute(Car car)
    {
        carRepository.save(car);
    }
}
