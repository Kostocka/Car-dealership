package peipo.ru.cardealership.domain.services;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;

@AllArgsConstructor
public class ConfiguratorService
{
    private final CompatibilityService compatibilityService;

    public void validateConfiguration(CarModel carModel)
    {
        compatibilityService.validate(carModel);
    }
}
