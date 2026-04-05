package peipo.ru.cardealership.infrastructure.persistence.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarModelEntity;

@Entity
@Table(name = "configured_car_order")
@Setter
@Getter
public class ConfiguredCarOrderEntity extends OrderEntity
{
    @OneToOne
    @JoinColumn(name = "configuration_id")
    private CarModelEntity configuration;

    @Enumerated(EnumType.STRING)
    private ConfiguredOrderStateEnum orderState;
}
