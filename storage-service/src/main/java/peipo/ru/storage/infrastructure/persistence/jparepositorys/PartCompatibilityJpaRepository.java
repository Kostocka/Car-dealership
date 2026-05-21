package peipo.ru.storage.infrastructure.persistence.jparepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.storage.infrastructure.persistence.entity.rules.PartCompatibilityEntity;
import peipo.ru.storage.infrastructure.persistence.entity.rules.PartCompatibilityId;

public interface PartCompatibilityJpaRepository
        extends JpaRepository<PartCompatibilityEntity, PartCompatibilityId>
{}