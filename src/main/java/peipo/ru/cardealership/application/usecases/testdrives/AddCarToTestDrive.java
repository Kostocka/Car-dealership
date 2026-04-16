package peipo.ru.cardealership.application.usecases.testdrives;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.repository.TestDriveCarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;

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

