package peipo.ru.storage.infrastructure.persistence.entity.rules;

import jakarta.persistence.Column;
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
public class ModelPartCompatibilityId implements Serializable
{
    @Column(name = "part_id")
    private UUID partId;

    @Column(name = "model_id")
    private UUID carModelId;
}
