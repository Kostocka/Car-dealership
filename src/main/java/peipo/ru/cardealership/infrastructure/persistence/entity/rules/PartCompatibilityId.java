package peipo.ru.cardealership.infrastructure.persistence.entity.rules;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PartCompatibilityId implements Serializable
{
    private UUID firstPartId;
    private UUID secondPartId;
}
