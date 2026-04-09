package peipo.ru.cardealership.infrastructure.persistence.jparepositorys;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.testdrive.TestDriveCarEntity;

public interface TestDriveCarJpaRepository extends JpaRepository<TestDriveCarEntity, UUID>
{}