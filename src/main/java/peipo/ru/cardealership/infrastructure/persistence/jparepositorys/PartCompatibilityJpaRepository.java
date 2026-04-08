package peipo.ru.cardealership.infrastructure.persistence.jparepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.rules.PartCompatibilityEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.rules.PartCompatibilityId;

public interface PartCompatibilityJpaRepository extends JpaRepository<PartCompatibilityEntity, PartCompatibilityId>
{}