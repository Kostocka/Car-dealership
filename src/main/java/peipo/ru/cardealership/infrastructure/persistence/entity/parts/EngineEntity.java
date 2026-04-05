package peipo.ru.cardealership.infrastructure.persistence.entity.parts;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.FuelType;

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
