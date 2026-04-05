package peipo.ru.cardealership.infrastructure.persistence.entity.cars;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "car")
@Getter
@Setter
public class CarEntity
{
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID carId;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModelEntity carModel;
}