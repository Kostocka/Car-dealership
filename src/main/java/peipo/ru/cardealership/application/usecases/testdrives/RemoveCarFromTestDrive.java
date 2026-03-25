package peipo.ru.cardealership.application.usecases.testdrives;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.repository.TestDriveCarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;

@AllArgsConstructor
public class RemoveCarFromTestDrive
{
    private TestDriveCarRepository testCarRepository;

    public void execute(CarId carId)
    {
        testCarRepository.removeCar(carId);
    }
}
