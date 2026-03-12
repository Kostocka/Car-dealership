package org.kostocka.cardealership.domain.services;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.models.TestDrive;
import org.kostocka.cardealership.domain.repository.TestDriveCarRepository;
import org.kostocka.cardealership.domain.repository.TestDriveRepository;
import org.kostocka.cardealership.domain.vo.id.CarId;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.TestDriveId;

import java.time.LocalDateTime;

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

        TestDrive testDrive =  new TestDrive(testDriveId, clientId, carId, LocalDateTime.now());
        testDriveRepository.save(testDrive);
        return testDrive;
    }
}
