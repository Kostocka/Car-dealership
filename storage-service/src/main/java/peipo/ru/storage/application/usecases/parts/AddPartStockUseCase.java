package peipo.ru.storage.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.repository.PartStockRepository;

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
