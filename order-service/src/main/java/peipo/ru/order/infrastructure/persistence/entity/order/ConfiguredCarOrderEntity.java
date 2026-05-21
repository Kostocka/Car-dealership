package peipo.ru.order.infrastructure.persistence.entity.order;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.DrivetrainType;

@Entity
@Table(name = "configured_car_order")
@Setter
@Getter
public class ConfiguredCarOrderEntity extends OrderEntity
{
    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ConfiguredOrderStateEnum orderState;
}