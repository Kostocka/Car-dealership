package peipo.ru.cardealership.infrastructure.persistence.entity.cars;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;

@Entity
@Table(name = "car")
@Getter
@Setter
public class CarEntity extends BaseEntity
{
    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModelEntity carModel;
}