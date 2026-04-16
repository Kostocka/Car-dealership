package peipo.ru.cardealership.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.rules.CompatibilityRule;

@Service
@AllArgsConstructor
public class CompatibilityService
{
    private List<CompatibilityRule> rules;

    public void validate(CarModel model)
    {
        for (CompatibilityRule rule : rules)
        {
            rule.validate(model);
        }
    }
}
