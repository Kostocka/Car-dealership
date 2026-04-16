package peipo.ru.cardealership.infrastructure.persistence.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

@Entity
@Table(name = "stock_car_order")
@Getter
@Setter
public class StockCarOrderEntity extends OrderEntity
{
    @OneToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @Enumerated(EnumType.STRING)
    private StockOrderStateEnum orderState;
}