package peipo.ru.storage.infrastructure.persistence.entity.stock;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "part_stock")
@Getter
@Setter
public class PartStockEntity
{
    @Id
    @Column(name = "part_id")
    private UUID part;

    private int quantity;
}
