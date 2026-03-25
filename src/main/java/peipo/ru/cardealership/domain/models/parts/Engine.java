package peipo.ru.cardealership.domain.models.parts;

import lombok.Getter;
import peipo.ru.cardealership.domain.vo.EnginePower;
import peipo.ru.cardealership.domain.vo.EngineVolume;
import peipo.ru.cardealership.domain.vo.FuelType;
import peipo.ru.cardealership.domain.vo.id.PartId;

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
