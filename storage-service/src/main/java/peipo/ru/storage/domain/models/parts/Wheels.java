package peipo.ru.storage.domain.models.parts;

import lombok.Getter;
import peipo.ru.common.vo.id.PartId;

@Getter
public class Wheels extends Part
{
    private final int size;

    public Wheels(PartId id, int size)
    {
        super(id);
        this.size = size;
    }
}
