package peipo.ru.order.application.usecases.testdrives;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.order.domain.models.TestDrive;
import peipo.ru.order.domain.repository.TestDriveCarRepository;
import peipo.ru.order.domain.repository.TestDriveRepository;
import peipo.ru.order.domain.vo.id.ClientId;
import peipo.ru.order.domain.vo.id.TestDriveId;

@Service
@AllArgsConstructor
public class CreateTestDriveRequest
{
    private final TestDriveRepository testDriveRepository;
    private final TestDriveCarRepository testDriveCarRepository;

    public TestDrive execute(ClientId clientId, CarId carId)
    {
        if (!testDriveCarRepository.isAvailable(carId))
        {
            throw new DomainValidationException("Car not available");
        }

        TestDrive testDrive = new TestDrive(TestDriveId.generate(), clientId, carId, LocalDateTime.now());
        testDriveRepository.save(testDrive);
        return testDrive;
    }
}
