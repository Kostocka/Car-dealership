package org.kostocka.cardealership.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.Car;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.repository.CarRepository;

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
