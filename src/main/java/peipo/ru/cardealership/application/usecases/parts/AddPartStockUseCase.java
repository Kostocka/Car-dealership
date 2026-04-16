package peipo.ru.cardealership.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.repository.PartStockRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;

@Service
@AllArgsConstructor
public class AddPartStockUseCase
{
    private PartStockRepository  partStockRepository;

    public void execute(PartId partId, int quantity)
    {
        partStockRepository.increase(partId, quantity);
    }
}
