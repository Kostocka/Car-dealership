package peipo.ru.storage.infrastructure.persistence.entity.cars.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.GearBoxType;

@Entity
@Table(name = "gearbox")
@Getter
@Setter
public class GearBoxEntity extends PartEntity
{
    @Enumerated(EnumType.STRING)
    private GearBoxType gearBoxType;
}
