package org.kostocka.cardealership.domain.services;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.repository.TestDriveCarRepository;
import org.kostocka.cardealership.domain.vo.id.CarId;

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
