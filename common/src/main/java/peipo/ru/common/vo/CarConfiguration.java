package peipo.ru.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import peipo.ru.common.vo.id.PartId;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class CarConfiguration
{
    private final String brand;
    private final String model;

    private final PartId body;
    private final PartId engine;
    private final PartId gearBox;
    private final PartId interior;
    private final PartId wheels;
    private final DrivetrainType drivetrainType;
    private final String color;
}
