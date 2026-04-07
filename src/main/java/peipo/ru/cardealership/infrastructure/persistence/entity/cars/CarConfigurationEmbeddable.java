package peipo.ru.cardealership.infrastructure.persistence.entity.cars;


import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.DrivetrainType;

@Setter
@Getter
@Embeddable
public class CarConfigurationEmbeddable
{
    private String brand;
    private String model;

    @Column(name = "body_id")
    private UUID body;
    
    @Column(name = "engine_id")
    private UUID engine;
    
    @Column(name = "gearbox_id")
    private UUID gearBox;
    
    @Column(name = "interior_id")
    private UUID interior;
    
    @Column(name = "wheels_id")
    private UUID wheels;

    @Enumerated(EnumType.STRING)
    @Column(name = "drivetrain_type")
    private DrivetrainType drivetrainType;

    @Column(name = "color")
    private String color;
}
