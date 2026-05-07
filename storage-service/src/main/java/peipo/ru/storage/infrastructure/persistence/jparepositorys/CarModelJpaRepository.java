package peipo.ru.storage.infrastructure.persistence.jparepositorys;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarModelEntity;

public interface CarModelJpaRepository extends JpaRepository<CarModelEntity, UUID>,
        JpaSpecificationExecutor<CarModelEntity>
{
    Optional<CarModelEntity> findByBrandAndModel(String brand, String model);
}