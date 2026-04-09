package peipo.ru.cardealership.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.CarModel;

@Service
@AllArgsConstructor
public class ConfiguratorService
{
    private final CompatibilityService compatibilityService;

    public void validateConfiguration(CarModel carModel)
    {
        compatibilityService.validate(carModel);
    }
}
