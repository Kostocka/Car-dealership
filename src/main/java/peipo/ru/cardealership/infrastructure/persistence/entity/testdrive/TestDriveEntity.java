package peipo.ru.cardealership.infrastructure.persistence.entity.testdrive;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Entity
@Table(name = "test_drive")
@Getter
@Setter
public class TestDriveEntity
{
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID testDriveId;

    private UUID clientId;

    @Column(name = "car_id")
    private UUID carId;

    private LocalDateTime startTime;
}
