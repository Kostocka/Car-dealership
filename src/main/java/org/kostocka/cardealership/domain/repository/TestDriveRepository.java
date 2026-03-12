package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.models.TestDrive;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.TestDriveId;

import java.util.List;

public interface TestDriveRepository extends Repository<TestDrive, TestDriveId>
{
    List<TestDrive> findByClientId(ClientId clientId);
}
