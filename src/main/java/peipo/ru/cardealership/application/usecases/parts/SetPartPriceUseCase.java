package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.repository.PartPriceRepository;
import peipo.ru.cardealership.domain.vo.Money;
import peipo.ru.cardealership.domain.vo.id.PartId;

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