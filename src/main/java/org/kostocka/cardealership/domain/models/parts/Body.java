package org.kostocka.cardealership.domain.models.parts;

import lombok.Getter;
import org.kostocka.cardealership.domain.vo.BodyType;
import org.kostocka.cardealership.domain.vo.id.PartId;

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
