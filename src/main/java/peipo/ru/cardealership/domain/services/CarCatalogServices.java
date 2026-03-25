package peipo.ru.cardealership.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;
import peipo.ru.cardealership.domain.repository.CarRepository;

@AllArgsConstructor
public class CarCatalogServices
{
    private CarRepository carRepository;

    public List<Car> find(Specification<CarModel> filter)
    {
        return carRepository.findAll()
                .stream()
                .filter(car -> filter.isSatisfiedBy(car.getConfiguration()))
                .toList();
    }
}
