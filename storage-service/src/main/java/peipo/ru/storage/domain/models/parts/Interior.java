package peipo.ru.storage.domain.models.parts;

import lombok.Getter;
import peipo.ru.common.vo.id.PartId;

@Getter
public class Interior extends Part
{
    private final String material;

    public Interior(PartId id, String material)
    {
        super(id);
        this.material = material;
    }
}