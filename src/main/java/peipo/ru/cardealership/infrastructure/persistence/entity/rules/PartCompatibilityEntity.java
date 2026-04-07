package peipo.ru.cardealership.infrastructure.persistence.entity.rules;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "part_compatibility")
@Getter
@Setter
public class PartCompatibilityEntity
{
    @EmbeddedId
    private PartCompatibilityId id;
}
