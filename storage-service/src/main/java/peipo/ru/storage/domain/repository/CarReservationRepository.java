package peipo.ru.storage.domain.repository;

import java.util.Optional;
import java.util.UUID;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.CarReservation;

public interface CarReservationRepository extends Repository<CarReservation, UUID>
{
    boolean existsActiveByCarId(CarId carId);

    Optional<CarReservation> findActiveByCarId(CarId carId);

    Optional<CarReservation> findByOrderId(OrderId orderId);
}
