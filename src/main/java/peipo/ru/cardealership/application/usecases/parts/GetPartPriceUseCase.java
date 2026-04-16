package peipo.ru.cardealership.application.usecases.parts;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.repository.PartPriceRepository;
import peipo.ru.cardealership.domain.vo.Money;
import peipo.ru.cardealership.domain.vo.id.PartId;

@Service
@AllArgsConstructor
public class GetPartPriceUseCase
{
    private PartPriceRepository partPriceRepository;

    public Money execute(PartId partId)
    {
        return partPriceRepository.getPartPrice(partId).orElse(new Money(BigDecimal.ZERO));
    }
}
