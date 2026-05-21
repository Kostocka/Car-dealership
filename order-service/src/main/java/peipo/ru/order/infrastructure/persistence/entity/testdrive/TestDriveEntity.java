package peipo.ru.order.infrastructure.persistence.entity.testdrive;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test_drive")
@Getter
@Setter
public class TestDriveEntity
{
    @Id
    @GeneratedValue()
    private UUID id;

    private UUID clientId;

    @Column(name = "car_id")
    private UUID carId;

    private LocalDateTime startTime;
}
