package peipo.ru.cardealership.application.usecases.testdrives;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.repository.TestDriveRepository;

@AllArgsConstructor
public class GetTestDrives
{
    private TestDriveRepository testCarRepository;

    public List<TestDrive> execute(Specification<TestDrive> spec)
    {
        return testCarRepository.findAll(spec);
    }
}
