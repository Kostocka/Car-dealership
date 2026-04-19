package peipo.ru.cardealership.infrastructure.persistence.entity.order;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stock_car_order")
@Getter
@Setter
public class StockCarOrderEntity extends OrderEntity
{
    @Column(name = "car_id")
    private UUID carId;

    @Enumerated(EnumType.STRING)
    private StockOrderStateEnum orderState;
}