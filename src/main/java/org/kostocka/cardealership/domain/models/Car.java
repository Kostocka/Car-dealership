package org.kostocka.cardealership.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kostocka.cardealership.domain.vo.id.CarId;

@Getter
@AllArgsConstructor
public class Car
{
    private final CarId carId;
    private final CarModel configuration;
}
