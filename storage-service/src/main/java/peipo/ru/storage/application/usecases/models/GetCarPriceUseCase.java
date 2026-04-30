package peipo.ru.storage.application.usecases.models;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.PartPriceRepository;
import peipo.ru.common.vo.Money;
import peipo.ru.common.vo.id.PartId;

@Service
@AllArgsConstructor
public class GetCarPriceUseCase
{
    private final PartPriceRepository partPriceRepository;

    public Money execute(CarModel selected)
    {
        var price = new Money(BigDecimal.ZERO);
        var selectedParts = List.of(
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
