package org.kostocka.cardealership.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.rules.CompatibilityRule;

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
