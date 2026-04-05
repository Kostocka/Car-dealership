package peipo.ru.cardealership.infrastructure.persistence.entity.order;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Table(name = "order")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class OrderEntity
{
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID orderId;

    private UUID clientId;
    private UUID managerId;
}
