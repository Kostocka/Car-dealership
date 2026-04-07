package peipo.ru.cardealership.infrastructure.persistence.entity.stock;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "part_price")
@Getter
@Setter
public class PartPriceEntity
{
    @Id
    @Column(name = "part_id")
    private UUID part;

    private BigDecimal price;
}
