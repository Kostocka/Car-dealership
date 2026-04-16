package peipo.ru.cardealership.infrastructure.web.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.FuelType;

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
