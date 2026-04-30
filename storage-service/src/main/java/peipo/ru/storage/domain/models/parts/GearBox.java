package peipo.ru.storage.domain.models.parts;

import lombok.Getter;
import peipo.ru.common.vo.GearBoxType;
import peipo.ru.common.vo.id.PartId;

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