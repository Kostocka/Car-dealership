package org.kostocka.cardealership.domain.services;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.repository.PartPriceRepository;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.PartId;

@AllArgsConstructor
public class CarPriceCalculatorService
{
    private final PartPriceRepository partPriceRepository;

    public Money calculate(CarModel selected)
    {
        Money price = new Money(BigDecimal.ZERO);

        List<PartId> selectedParts = List.of(
                selected.getBody().getId(),
                selected.getEngine().getId(),
                selected.getGearBox().getId(),
                selected.getWheels().getId(),
                selected.getInterior().getId()
        );

        for (PartId partId : selectedParts)
        {
            Money partsPrice = partPriceRepository.getPartPrice(partId).orElse(new Money(BigDecimal.ZERO));
            price = price.add(partsPrice);
        }

        return price;
    }
}
