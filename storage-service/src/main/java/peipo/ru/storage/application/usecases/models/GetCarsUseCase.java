package peipo.ru.storage.application.usecases.models;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.repository.CarRepository;

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
