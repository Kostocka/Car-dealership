package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.models.TestDrive;
import org.kostocka.cardealership.domain.repository.TestDriveRepository;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.TestDriveId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTestDriveRepository implements TestDriveRepository
{
    private final Map<TestDriveId, TestDrive> testDrives =  new HashMap<>();

    @Override
    public List<TestDrive> findByClientId(ClientId clientId) {
        return testDrives.values()
                .stream()
                .filter(e -> e.getClientId().equals(clientId))
                .toList();
    }

    @Override
    public Optional<TestDrive> findById(TestDriveId testDriveId) {
        return Optional.ofNullable(testDrives.get(testDriveId));
    }

    @Override
    public List<TestDrive> findAll() {
        return testDrives.values().stream().toList();
    }

    @Override
    public void save(TestDrive entity) {
        testDrives.put(entity.getTestDriveId(), entity);
    }

    @Override
    public void delete(TestDriveId testDriveId) {
        testDrives.remove(testDriveId);
    }
}
