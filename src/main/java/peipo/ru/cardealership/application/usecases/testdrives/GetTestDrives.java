package peipo.ru.cardealership.application.usecases.testdrives;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.repository.TestDriveRepository;

@Service
@AllArgsConstructor
public class GetTestDrives
{
    private TestDriveRepository testCarRepository;

    public List<TestDrive> execute()
    {
        return testCarRepository.findAll();
    }
}
