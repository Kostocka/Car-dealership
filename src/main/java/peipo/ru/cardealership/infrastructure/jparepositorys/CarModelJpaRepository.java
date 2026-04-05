package peipo.ru.cardealership.infrastructure.jparepositorys;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarModelEntity;

public interface CarModelJpaRepository extends JpaRepository<CarModelEntity, UUID>,
        JpaSpecificationExecutor<CarModelEntity>
{}