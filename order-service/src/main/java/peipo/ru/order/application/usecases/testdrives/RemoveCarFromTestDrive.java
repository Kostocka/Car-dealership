package peipo.ru.order.application.usecases.testdrives;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.order.domain.repository.TestDriveCarRepository;

@Service
@AllArgsConstructor
public class RemoveCarFromTestDrive
{
    private TestDriveCarRepository testCarRepository;

    public void execute(CarId carId)
    {
        testCarRepository.removeCar(carId);
    }
}
