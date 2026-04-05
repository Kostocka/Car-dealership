package peipo.ru.cardealership.infrastructure.jparepositorys;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.PartEntity;

public interface PartJpaRepository extends JpaRepository<PartEntity, UUID>
{}