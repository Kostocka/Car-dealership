package peipo.ru.storage.infrastructure.web.dto.parts;

import java.util.UUID;
import lombok.*;
import peipo.ru.common.vo.FuelType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EngineDto
{
    private UUID id;
    private int volume;
    private int power;
    private FuelType fuelType;
}