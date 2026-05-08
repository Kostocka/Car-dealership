package peipo.ru.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import peipo.ru.common.vo.id.PartId;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CarConfiguration
{
    private String brand;
    private String model;

    private PartId body;
    private PartId engine;
    private PartId gearBox;
    private PartId interior;
    private PartId wheels;

    private DrivetrainType drivetrainType;

    private String color;
}
