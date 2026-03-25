package peipo.ru.cardealership.domain.services;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.repository.TestDriveCarRepository;
import peipo.ru.cardealership.domain.repository.TestDriveRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.TestDriveId;

@AllArgsConstructor
public class TestDriveService
{
    private final TestDriveRepository testDriveRepository;
    private final TestDriveCarRepository testDriveCarRepository;

    public TestDrive signUp(ClientId clientId, CarId carId)
    {
        TestDriveId testDriveId = TestDriveId.generate();
        if (!testDriveCarRepository.isAvailable(carId))
        {
            throw new DomainValidationException("Car not available");
        }

        TestDrive testDrive = new TestDrive(testDriveId, clientId, carId, LocalDateTime.now());
        testDriveRepository.save(testDrive);
        return testDrive;
    }
}