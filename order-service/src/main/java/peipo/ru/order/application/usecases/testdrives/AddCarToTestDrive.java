package peipo.ru.order.application.usecases.testdrives;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.order.domain.repository.TestDriveCarRepository;

@Service
@RequiredArgsConstructor
public class AddCarToTestDrive
{
    private final TestDriveCarRepository testCarRepository;

    public void execute(CarId carId)
    {
        testCarRepository.addCar(carId);
    }
}

