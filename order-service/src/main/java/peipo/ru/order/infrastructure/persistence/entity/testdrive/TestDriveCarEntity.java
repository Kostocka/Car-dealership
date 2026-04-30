package peipo.ru.order.infrastructure.persistence.entity.testdrive;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test_drive_cars")
@Getter
@Setter
@NoArgsConstructor
public class TestDriveCarEntity
{
    @Id
    private UUID carId;

    @Column(name = "is_available")
    private boolean available;

    public TestDriveCarEntity(UUID carId)
    {
        this.carId = carId;
        this.available = true;
    }
}
