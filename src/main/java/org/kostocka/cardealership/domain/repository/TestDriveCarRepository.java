package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.vo.id.CarId;
import org.kostocka.cardealership.domain.vo.id.CarModelId;

import java.util.List;

public interface TestDriveCarRepository
{
    void addCar(CarId carId);

    void removeCar(CarId carId);

    boolean isAvailable(CarId carId);

    List<CarModelId> findAll();
}
