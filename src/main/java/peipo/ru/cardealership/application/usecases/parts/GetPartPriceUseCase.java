package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.repository.PartPriceRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;

@AllArgsConstructor
public class GetPartPriceUseCase
{
    private PartPriceRepository partPriceRepository;

    public void execute(PartId partId)
    {
        partPriceRepository.getPartPrice(partId);
    }
}
