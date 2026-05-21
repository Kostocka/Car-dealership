package peipo.ru.storage.infrastructure.persistence.jparepositorys;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.storage.infrastructure.persistence.entity.assembly.ConfiguredAssemblyOrderEntity;

public interface ConfiguredAssemblyOrderJpaRepository
        extends JpaRepository<ConfiguredAssemblyOrderEntity, UUID>
{}
