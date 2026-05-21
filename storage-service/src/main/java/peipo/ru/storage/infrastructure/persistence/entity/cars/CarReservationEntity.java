package peipo.ru.storage.infrastructure.persistence.entity.cars;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.storage.domain.models.ReservationStatus;

@Getter
@Setter
@Entity
@Table(name = "car_reservations")
public class CarReservationEntity
{
    @Id
    private UUID id;

    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
