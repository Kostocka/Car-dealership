package org.kostocka.cardealership.domain.models.parts;

import lombok.Getter;
import org.kostocka.cardealership.domain.vo.GearBoxType;
import org.kostocka.cardealership.domain.vo.id.PartId;

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