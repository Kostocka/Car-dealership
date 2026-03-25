package peipo.ru.cardealership.domain.models.parts;

import lombok.Getter;
import peipo.ru.cardealership.domain.vo.BodyType;
import peipo.ru.cardealership.domain.vo.id.PartId;

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
