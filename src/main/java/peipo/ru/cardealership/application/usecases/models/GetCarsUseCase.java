package peipo.ru.cardealership.application.usecases.models;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.repository.CarRepository;

@Service
@AllArgsConstructor
public class GetCarsUseCase
{
    private CarRepository carRepository;

    public List<Car> execute(Filter<CarModel> spec)
    {
        return carRepository.findAll(spec);
    }
}
