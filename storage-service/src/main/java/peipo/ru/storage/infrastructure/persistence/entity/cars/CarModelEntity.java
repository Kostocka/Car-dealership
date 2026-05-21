package peipo.ru.storage.infrastructure.persistence.entity.cars;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.DrivetrainType;
import peipo.ru.storage.infrastructure.persistence.entity.BaseEntity;
import peipo.ru.storage.infrastructure.persistence.entity.cars.parts.*;

@Entity
@Table(name = "car_model")
@Getter
@Setter
public class CarModelEntity extends BaseEntity
{
    private String brand;
    private String model;

    @ManyToOne()
    @JoinColumn(name = "body_id")
    private BodyEntity body;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private EngineEntity engine;

    @ManyToOne
    @JoinColumn(name = "gearbox_id")
    private GearBoxEntity gearBox;

    @ManyToOne
    @JoinColumn(name = "interior_id")
    private InteriorEntity interior;

    @ManyToOne
    @JoinColumn(name = "wheels_id")
    private WheelsEntity wheels;

    @Enumerated(EnumType.STRING)
    private DrivetrainType drivetrainType;

    private String color;
}