package peipo.ru.storage.infrastructure.persistence.entity.rules;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "model_part_compatibility")
@Getter
@Setter
public class ModelPartCompatibilityEntity
{
    @EmbeddedId
    private ModelPartCompatibilityId id;
}
