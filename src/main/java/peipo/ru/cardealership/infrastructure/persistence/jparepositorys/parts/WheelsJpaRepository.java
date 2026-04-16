package peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.WheelsEntity;

public interface WheelsJpaRepository extends JpaRepository<WheelsEntity, UUID>
{}