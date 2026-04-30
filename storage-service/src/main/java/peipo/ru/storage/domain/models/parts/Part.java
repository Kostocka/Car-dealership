package peipo.ru.storage.domain.models.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.vo.id.PartId;

@Getter
@AllArgsConstructor
public abstract class Part
{
    private final PartId id;
}
