package peipo.ru.common.dto.parts;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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