package peipo.ru.storage.infrastructure.web.dto.cars;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.vo.DrivetrainType;
import peipo.ru.storage.infrastructure.web.dto.parts.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarConfigurationDto
{
    UUID id;
    String brand;
    String model;
    BodyDto body;
    EngineDto engine;
    GearBoxDto gearBox;
    InteriorDto interior;
    WheelsDto wheels;
    DrivetrainType drivetrainType;
    String color;
}