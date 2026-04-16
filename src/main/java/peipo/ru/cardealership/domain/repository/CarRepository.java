package peipo.ru.cardealership.domain.repository;

import java.util.List;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.filters.Filter;
import peipo.ru.cardealership.domain.vo.id.CarId;

public interface CarRepository extends Repository<Car, CarId>
{
    List<Car> findAll(Filter<CarModel> filter);
}