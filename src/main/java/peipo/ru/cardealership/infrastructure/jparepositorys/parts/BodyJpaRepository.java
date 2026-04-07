package peipo.ru.cardealership.infrastructure.jparepositorys.parts;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.BodyEntity;

public interface BodyJpaRepository extends JpaRepository<BodyEntity, UUID>
{}