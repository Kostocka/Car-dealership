package org.kostocka.cardealership.domain.models.rules;

import org.kostocka.cardealership.domain.models.CarModel;

public interface CompatibilityRule
{
    void validate(CarModel carModel);
}
