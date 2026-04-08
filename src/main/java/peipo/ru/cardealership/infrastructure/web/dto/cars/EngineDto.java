package peipo.ru.cardealership.infrastructure.web.dto.cars;

import lombok.*;
import peipo.ru.cardealership.domain.vo.FuelType;

import java.util.UUID;

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