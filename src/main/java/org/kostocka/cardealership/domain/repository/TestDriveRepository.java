package org.kostocka.cardealership.domain.repository;

import java.util.List;
import org.kostocka.cardealership.domain.models.TestDrive;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.TestDriveId;

public interface TestDriveRepository extends Repository<TestDrive, TestDriveId>
{
    List<TestDrive> findByClientId(ClientId clientId);
}
