package peipo.ru.cardealership.domain.models.rules;

import java.util.List;
import peipo.ru.cardealership.domain.repository.ModelPartCompatibilityRepository;
import peipo.ru.cardealership.domain.repository.PartCompatibilityRepository;

public class CompatibilityRuleFactory
{
    public static List<CompatibilityRule> createCompatibilityRules(
            ModelPartCompatibilityRepository modelPartCompatibilityRepository,
            PartCompatibilityRepository partCompatibilityRepository)
    {
        return List.of(
                new ModelPartCompatibilityRule(
                        modelPartCompatibilityRepository,
                        carModel -> carModel.getEngine().getId()
                ),
                new ModelPartCompatibilityRule(
                        modelPartCompatibilityRepository,
                        carModel -> carModel.getBody().getId()
                ),
                new ModelPartCompatibilityRule(
                        modelPartCompatibilityRepository,
                        carModel -> carModel.getInterior().getId()
                ),
                new  ModelPartCompatibilityRule(
                        modelPartCompatibilityRepository,
                        carModel -> carModel.getGearBox().getId()
                ),
                new  ModelPartCompatibilityRule(
                        modelPartCompatibilityRepository,
                        carModel -> carModel.getWheels().getId()
                ),
                new PartPairCompatibilityRule(
                        partCompatibilityRepository,
                        carModel -> carModel.getEngine().getId(),
                        carModel -> carModel.getGearBox().getId()
                )
        );
    }
}
