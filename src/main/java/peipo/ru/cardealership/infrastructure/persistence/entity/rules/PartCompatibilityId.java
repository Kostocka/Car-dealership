package peipo.ru.cardealership.infrastructure.persistence.entity.rules;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartCompatibilityId implements Serializable
{
    private UUID firstPartId;
    private UUID secondPartId;
}
