package peipo.ru.cardealership.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.vo.id.CarId;

public interface CarRepository extends JpaRepository<Car, CarId>, JpaSpecificationExecutor<Car>
{
}