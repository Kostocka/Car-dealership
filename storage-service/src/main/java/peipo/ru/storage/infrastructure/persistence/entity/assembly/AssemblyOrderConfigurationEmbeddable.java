package peipo.ru.storage.infrastructure.persistence.entity.assembly;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.DrivetrainType;

@Embeddable
@Getter
@Setter
public class AssemblyOrderConfigurationEmbeddable
{
    private String brand;
    private String model;

    private UUID bodyId;
    private UUID engineId;
    private UUID gearBoxId;
    private UUID interiorId;
    private UUID wheelsId;

    @Enumerated(EnumType.STRING)
    private DrivetrainType drivetrainType;

    private String color;
}
