package peipo.ru.cardealership.domain.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.vo.id.CarId;

public interface TestDriveCarRepository extends JpaSpecificationExecutor<Car>
{
    void addCar(CarId carId);

    void removeCar(CarId carId);

    boolean isAvailable(CarId carId);
}
