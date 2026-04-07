package peipo.ru.cardealership.infrastructure.persistence.entity.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.GearBoxType;

@Entity
@Table(name = "gearbox")
@Getter
@Setter
public class GearBoxEntity extends PartEntity
{
    @Enumerated(EnumType.STRING)
    private GearBoxType gearBoxType;
}
