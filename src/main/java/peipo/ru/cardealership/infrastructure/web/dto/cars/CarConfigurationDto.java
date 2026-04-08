package peipo.ru.cardealership.infrastructure.web.dto.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.DrivetrainType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarConfigurationDto
{
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