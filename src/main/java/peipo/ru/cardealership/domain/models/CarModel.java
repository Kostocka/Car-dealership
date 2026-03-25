package peipo.ru.cardealership.domain.models;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.models.parts.GearBox;
import peipo.ru.cardealership.domain.models.parts.Interior;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.vo.DrivetrainType;
import peipo.ru.cardealership.domain.vo.id.CarModelId;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class CarModel
{
    private final CarModelId modelId;

    private final String brand;
    private final String model;

    private final Body body;
    private final Engine engine;
    private final GearBox gearBox;
    private final Interior interior;
    private final Wheels wheels;
    private final DrivetrainType drivetrainType;
    private final Color color;
}