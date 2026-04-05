package peipo.ru.cardealership.infrastructure.persistence.entity.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;

@Entity
@Table(name = "part_price")
@Getter
@Setter
public class PartPriceEntity extends BaseEntity
{
    private BigDecimal price;
}
