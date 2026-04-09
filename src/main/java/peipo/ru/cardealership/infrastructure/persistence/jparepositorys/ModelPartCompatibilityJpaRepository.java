package peipo.ru.cardealership.infrastructure.persistence.jparepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.rules.ModelPartCompatibilityEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.rules.ModelPartCompatibilityId;

public interface ModelPartCompatibilityJpaRepository
        extends JpaRepository<ModelPartCompatibilityEntity, ModelPartCompatibilityId>
{}