package peipo.ru.storage.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.vo.Money;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.repository.PartPriceRepository;

@Service
@AllArgsConstructor
public class SetPartPriceUseCase
{
    private PartPriceRepository  partPriceRepository;

    public void execute(PartId  partId, Money price)
    {
        partPriceRepository.setPartPrice(partId, price);
    }
}