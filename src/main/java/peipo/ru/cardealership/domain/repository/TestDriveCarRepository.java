package peipo.ru.cardealership.domain.repository;

import peipo.ru.cardealership.domain.vo.id.CarId;

public interface TestDriveCarRepository
{
    void addCar(CarId carId);

    void removeCar(CarId carId);

    boolean isAvailable(CarId carId);
}
