package peipo.ru.cardealership.infrastructure.persistence.entity.rules;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;

@Entity
@Table(name = "part_compatibility")
@Getter
@Setter
public class PartCompatibilityEntity extends BaseEntity
{
    private UUID firstPartId;

    private UUID secondPartId;
}
