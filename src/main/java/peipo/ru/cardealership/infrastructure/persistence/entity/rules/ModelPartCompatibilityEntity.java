package peipo.ru.cardealership.infrastructure.persistence.entity.rules;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;

@Entity
@Table(name = "model_part_compatibility")
@Getter
@Setter
public class ModelPartCompatibilityEntity
{
    @EmbeddedId
    private ModelPartCompatibilityId id;
}
