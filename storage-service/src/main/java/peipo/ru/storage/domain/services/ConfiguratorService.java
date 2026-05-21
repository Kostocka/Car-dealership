package peipo.ru.storage.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.CarModel;

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
