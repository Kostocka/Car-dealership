package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.models.Car;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.vo.id.CarId;

import java.util.List;

public interface CarRepository extends Repository<Car, CarId>
{
    List<Car> find(Specification<Car> spec);
}
