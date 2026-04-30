package peipo.ru.order.infrastructure.persistence.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.order.infrastructure.persistence.entity.cars.CarConfigurationEmbeddable;

@Entity
@Table(name = "configured_car_order")
@Setter
@Getter
public class ConfiguredCarOrderEntity extends OrderEntity
{
    @Embedded
    private CarConfigurationEmbeddable configuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ConfiguredOrderStateEnum orderState;
}