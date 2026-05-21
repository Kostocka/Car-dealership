package peipo.ru.order.domain.repository;

import peipo.ru.common.vo.id.CarId;

public interface TestDriveCarRepository
{
    void addCar(CarId carId);

    void removeCar(CarId carId);

    boolean isAvailable(CarId carId);
}
