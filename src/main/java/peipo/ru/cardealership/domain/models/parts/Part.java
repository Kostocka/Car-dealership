package peipo.ru.cardealership.domain.models.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.vo.id.PartId;

@Getter
@AllArgsConstructor
public abstract class Part
{
    private final PartId id;
}
