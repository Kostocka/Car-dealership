package peipo.ru.storage.infrastructure.persistence.entity.cars.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.FuelType;

@Entity
@Table(name = "engine")
@Getter
@Setter
public class EngineEntity extends PartEntity
{
    private int volume;
    private int power;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
}
