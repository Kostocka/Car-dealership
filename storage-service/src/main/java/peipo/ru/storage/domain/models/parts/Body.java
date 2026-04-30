package peipo.ru.storage.domain.models.parts;

import lombok.Getter;
import peipo.ru.common.vo.BodyType;
import peipo.ru.common.vo.id.PartId;

@Getter
public class Body extends Part
{
    private final BodyType type;

    public Body(PartId id, BodyType type)
    {
        super(id);
        this.type = type;
    }
}
