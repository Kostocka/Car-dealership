package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.repository.PartStockRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;

@AllArgsConstructor
public class AddPartStockUseCases
{
    private PartStockRepository  partStockRepository;

    public void execute(PartId partId, int quantity)
    {
        partStockRepository.increase(partId, quantity);
    }
}
