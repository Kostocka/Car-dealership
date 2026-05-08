package peipo.ru.storage.domain.repository;

import java.util.List;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;

public interface CarRepository extends Repository<Car, CarId>
{
    List<Car> findAll(Filter<CarModel> filter);
}