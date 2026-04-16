package peipo.ru.cardealership.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.cardealership.domain.vo.id.CarId;

@Getter
@AllArgsConstructor
public class Car
{
    private final CarId carId;
    private final CarModel configuration;
}
