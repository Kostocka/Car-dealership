package peipo.ru.cardealership.domain.repository;

import java.util.List;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.TestDriveId;

public interface TestDriveRepository extends Repository<TestDrive, TestDriveId>
{
    List<TestDrive> findByClientId(ClientId clientId);
}
