package peipo.ru.cardealership.infrastructure.persistence.entity.cars;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.DrivetrainType;
import peipo.ru.cardealership.infrastructure.persistence.entity.parts.*;

@Setter
@Getter
@Embeddable
public class CarConfigurationEmbeddable
{
    private UUID modelId;

    private String brand;
    private String model;

    @ManyToOne()
    @JoinColumn(name = "body_id")
    private BodyEntity body;

    @ManyToOne()
    @JoinColumn(name = "engine_id")
    private EngineEntity engine;

    @ManyToOne()
    @JoinColumn(name = "gearbox_id")
    private GearBoxEntity gearBox;

    @ManyToOne()
    @JoinColumn(name = "interior_id")
    private InteriorEntity interior;

    @ManyToOne()
    @JoinColumn(name = "wheels_id")
    private WheelsEntity wheels;

    @Enumerated(EnumType.STRING)
    @Column(name = "drivetrain_type")
    private DrivetrainType drivetrainType;

    @Column(name = "color")
    private String color;
}
