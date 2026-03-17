package org.kostocka.cardealership.domain.repository;

import java.util.List;
import org.kostocka.cardealership.domain.vo.id.CarId;

public interface TestDriveCarRepository
{
    void addCar(CarId carId);

    void removeCar(CarId carId);

    boolean isAvailable(CarId carId);

    List<CarId> findAll();
}
