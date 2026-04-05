package peipo.ru.cardealership.infrastructure.persistence.entity.rules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "model_part_compatibility")
@Getter
@Setter
public class ModelPartCompatibilityEntity
{
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private UUID partId;

    private UUID carModelId;
}
