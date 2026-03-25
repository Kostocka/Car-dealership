package peipo.ru.cardealership.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.vo.id.TestDriveId;

public interface TestDriveRepository extends JpaRepository<TestDrive, TestDriveId>,
        JpaSpecificationExecutor<TestDrive> {}
