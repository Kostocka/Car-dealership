package peipo.ru.storage.infrastructure.persistence.jparepositorys.parts;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.GearBoxEntity;

public interface GearBoxJpaRepository extends JpaRepository<GearBoxEntity, UUID>
{}