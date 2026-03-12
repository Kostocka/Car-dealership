package org.kostocka.cardealership.domain.services;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.rules.CompatibilityRule;

import java.util.List;

@AllArgsConstructor
public class CompatibilityService
{
    private List<CompatibilityRule> rules;

    public void validate(CarModel model)
    {
        for(CompatibilityRule rule : rules)
        {
            rule.validate(model);
        }
    }
}
