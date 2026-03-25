package peipo.ru.cardealership.infrastructure.inMemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.repository.TestDriveRepository;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.TestDriveId;

public class InMemoryTestDriveRepository implements TestDriveRepository
{
    private final Map<TestDriveId, TestDrive> testDrives = new HashMap<>();

    @Override
    public List<TestDrive> findByClientId(ClientId clientId)
    {
        return testDrives.values()
                .stream()
                .filter(e -> e.getClientId().equals(clientId))
                .toList();
    }

    @Override
    public Optional<TestDrive> findById(TestDriveId testDriveId)
    {
        return Optional.ofNullable(testDrives.get(testDriveId));
    }

    @Override
    public List<TestDrive> findAll()
    {
        return testDrives.values().stream().toList();
    }

    @Override
    public void save(TestDrive entity)
    {
        testDrives.put(entity.getTestDriveId(), entity);
    }

    @Override
    public void delete(TestDriveId testDriveId)
    {
        testDrives.remove(testDriveId);
    }
}
