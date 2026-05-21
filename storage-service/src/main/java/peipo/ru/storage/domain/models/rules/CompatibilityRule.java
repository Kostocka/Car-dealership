package peipo.ru.storage.domain.models.rules;

import peipo.ru.storage.domain.models.CarModel;

public interface CompatibilityRule
{
    void validate(CarModel carModel);
}
