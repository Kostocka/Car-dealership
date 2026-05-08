package peipo.ru.common.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.vo.DrivetrainType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguredCarRequest
{
    private String brand;
    private String model;

    private UUID body;
    private UUID engine;
    private UUID gearBox;
    private UUID interior;
    private UUID wheels;

    private DrivetrainType drivetrainType;

    private String color;
}
