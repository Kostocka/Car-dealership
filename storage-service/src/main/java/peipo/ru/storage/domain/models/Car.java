package peipo.ru.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.vo.id.CarId;

@Getter
@AllArgsConstructor
public class Car
{
    private final CarId carId;
    private final CarModel configuration;
}
