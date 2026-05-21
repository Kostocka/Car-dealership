package peipo.ru.common.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.vo.FuelType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEngineRequest
{
    private int power;
    private int volume;
    private FuelType fuelType;
}
