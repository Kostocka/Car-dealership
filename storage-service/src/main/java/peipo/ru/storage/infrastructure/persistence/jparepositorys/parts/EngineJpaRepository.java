package peipo.ru.storage.infrastructure.persistence.jparepositorys.parts;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.EngineEntity;

public interface EngineJpaRepository extends JpaRepository<EngineEntity, UUID>
{}