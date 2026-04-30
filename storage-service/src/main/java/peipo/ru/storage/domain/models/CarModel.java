package peipo.ru.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import peipo.ru.storage.domain.models.parts.Body;
import peipo.ru.storage.domain.models.parts.Engine;
import peipo.ru.storage.domain.models.parts.GearBox;
import peipo.ru.storage.domain.models.parts.Interior;
import peipo.ru.storage.domain.models.parts.Wheels;
import peipo.ru.common.vo.DrivetrainType;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class CarModel
{
    private final String brand;
    private final String model;

    private final Body body;
    private final Engine engine;
    private final GearBox gearBox;
    private final Interior interior;
    private final Wheels wheels;
    private final DrivetrainType drivetrainType;
    private final String color;
}