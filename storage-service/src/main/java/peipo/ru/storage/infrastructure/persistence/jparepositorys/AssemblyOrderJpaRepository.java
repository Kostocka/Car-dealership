package peipo.ru.storage.infrastructure.persistence.jparepositorys;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.storage.infrastructure.persistence.entity.assembly.AssemblyOrderEntity;

public interface AssemblyOrderJpaRepository extends JpaRepository<AssemblyOrderEntity, UUID>
{
    Optional<AssemblyOrderEntity> findBySourceOrderId(UUID sourceOrderId);
}
