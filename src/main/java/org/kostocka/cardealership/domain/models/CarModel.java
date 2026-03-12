package org.kostocka.cardealership.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kostocka.cardealership.domain.models.parts.*;
import org.kostocka.cardealership.domain.vo.DrivetrainType;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.CarModelId;

import java.awt.*;


@Getter
@Setter
@AllArgsConstructor
public class CarModel implements Cloneable
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

    @Override
    public CarModel clone()
    {
        try
        {
            return (CarModel) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
    }
}