package peipo.ru.storage.infrastructure.persistence.jparepositorys;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.storage.domain.models.ReservationStatus;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarReservationEntity;

public interface CarReservationJpaRepository extends JpaRepository<CarReservationEntity, UUID>
{
    boolean existsByCarIdAndStatus(UUID carId, ReservationStatus status);

    Optional<CarReservationEntity> findByCarIdAndStatus(UUID carId, ReservationStatus status);

    Optional<CarReservationEntity> findByOrderId(UUID orderId);
}
