package peipo.ru.cardealership.domain.repository;

import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.specifications.Specification;
import peipo.ru.cardealership.domain.vo.id.CarId;

import java.util.List;

public interface CarRepository extends Repository<Car, CarId>
{
    List<Car> findAll(Specification<Car> spec);
}