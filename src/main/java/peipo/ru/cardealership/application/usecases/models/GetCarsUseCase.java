package peipo.ru.cardealership.application.usecases.models;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.repository.CarRepository;

@AllArgsConstructor
public class GetCarsUseCase
{
    private CarRepository carRepository;

    public List<Car> execute(Specification<Car> spec)
    {
        return carRepository.findAll(spec);
    }
}
