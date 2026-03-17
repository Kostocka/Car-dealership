package org.kostocka.cardealership.domain.models;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.kostocka.cardealership.domain.models.parts.Body;
import org.kostocka.cardealership.domain.models.parts.Engine;
import org.kostocka.cardealership.domain.models.parts.GearBox;
import org.kostocka.cardealership.domain.models.parts.Interior;
import org.kostocka.cardealership.domain.models.parts.Wheels;
import org.kostocka.cardealership.domain.vo.DrivetrainType;
import org.kostocka.cardealership.domain.vo.id.CarModelId;

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