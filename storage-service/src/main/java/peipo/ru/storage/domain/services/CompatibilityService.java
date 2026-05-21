package peipo.ru.storage.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.rules.CompatibilityRule;

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
