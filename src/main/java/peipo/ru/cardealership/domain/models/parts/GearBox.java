package peipo.ru.cardealership.domain.models.parts;

import lombok.Getter;
import peipo.ru.cardealership.domain.vo.GearBoxType;
import peipo.ru.cardealership.domain.vo.id.PartId;

@Getter
public class GearBox extends Part
{
    private final GearBoxType gearBoxType;

    public GearBox(PartId id, GearBoxType gearBoxType)
    {
        super(id);
        this.gearBoxType = gearBoxType;
    }
}