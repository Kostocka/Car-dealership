package peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.InteriorEntity;

public interface InteriorJpaRepository extends JpaRepository<InteriorEntity, UUID>
{}