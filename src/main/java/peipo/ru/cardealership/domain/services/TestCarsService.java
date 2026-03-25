package peipo.ru.cardealership.domain.services;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.repository.TestDriveCarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;

@AllArgsConstructor
public class TestCarsService
{
    private final TestDriveCarRepository testDriveCarRepository;

    public void addCar(CarId carId)
    {
        testDriveCarRepository.addCar(carId);
    }

    public void removeCar(CarId carId)
    {
        testDriveCarRepository.removeCar(carId);
    }
}
