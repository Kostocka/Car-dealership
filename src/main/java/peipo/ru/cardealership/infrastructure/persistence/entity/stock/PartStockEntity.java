package peipo.ru.cardealership.infrastructure.persistence.entity.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;

@Entity
@Table(name = "part_stock")
@Getter
@Setter
public class PartStockEntity extends BaseEntity
{
    private int quantity;
}
