package peipo.ru.storage.domain.models;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;

@Getter
@AllArgsConstructor
public class CarReservation
{
    private final UUID id;
    private final CarId carId;
    private final OrderId orderId;
    private final Instant createdAt;

    @Setter
    private ReservationStatus status;
}
