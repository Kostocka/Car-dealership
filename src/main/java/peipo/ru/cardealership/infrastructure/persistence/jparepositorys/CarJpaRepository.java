package peipo.ru.cardealership.infrastructure.persistence.jparepositorys;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

public interface CarJpaRepository extends JpaRepository<CarEntity, UUID>, JpaSpecificationExecutor<CarEntity>
{}