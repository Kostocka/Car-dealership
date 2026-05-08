package peipo.ru.storage.application.usecases.parts;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.vo.Money;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.repository.PartPriceRepository;

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
