package peipo.ru.cardealership.infrastructure.persistence.entity.parts;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
@Table(name = "part")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PartEntity
{
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID partId;
}