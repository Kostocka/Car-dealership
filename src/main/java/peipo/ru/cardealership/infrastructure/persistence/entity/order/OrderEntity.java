package peipo.ru.cardealership.infrastructure.persistence.entity.order;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;


@Entity
@Table(name = "order")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class OrderEntity extends BaseEntity
{
    private UUID clientId;
    private UUID managerId;
}
