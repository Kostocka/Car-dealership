package peipo.ru.order.infrastructure.persistence.jparepositorys;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.order.infrastructure.persistence.entity.order.ConfiguredCarOrderEntity;

public interface ConfiguredOrderJpaRepository extends JpaRepository<ConfiguredCarOrderEntity, UUID>
{
    List<ConfiguredCarOrderEntity> findByClientId(UUID clientId);
}