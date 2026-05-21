package peipo.ru.storage.infrastructure.persistence.entity.cars.parts;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wheels")
@Getter
@Setter
public class WheelsEntity extends PartEntity
{
    private int size;
}
