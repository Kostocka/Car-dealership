package org.kostocka.cardealership.domain.models.parts;

import lombok.Getter;
import org.kostocka.cardealership.domain.vo.EnginePower;
import org.kostocka.cardealership.domain.vo.EngineVolume;
import org.kostocka.cardealership.domain.vo.FuelType;
import org.kostocka.cardealership.domain.vo.id.PartId;

@Getter
public class Engine extends Part
{
    private final EngineVolume volume;
    private final EnginePower power;
    private final FuelType fuelType;

    public Engine(PartId id, FuelType fuelType, EnginePower enginePower, EngineVolume engineVolume)
    {
        super(id);
        this.fuelType = fuelType;
        this.power = enginePower;
        this.volume = engineVolume;
    }
}
