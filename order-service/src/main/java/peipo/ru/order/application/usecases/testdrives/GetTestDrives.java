package peipo.ru.order.application.usecases.testdrives;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.order.domain.models.TestDrive;
import peipo.ru.order.domain.repository.TestDriveRepository;

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
