package peipo.ru.cardealership.infrastructure.persistence.entity.rules;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ModelPartCompatibilityId implements Serializable
{
    @Column(name = "part_id")
    private UUID partId;

    @Column(name = "model_id")
    private UUID carModelId;
}
