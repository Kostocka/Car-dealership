package peipo.ru.order.infrastructure.persistence.jparepositorys;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.order.infrastructure.persistence.entity.testdrive.TestDriveEntity;

public interface TestDriveJpaRepository extends JpaRepository<TestDriveEntity, UUID>
{}