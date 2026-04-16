package peipo.ru.cardealership.domain.models.rules;

import peipo.ru.cardealership.domain.models.CarModel;

public interface CompatibilityRule
{
    void validate(CarModel carModel);
}
