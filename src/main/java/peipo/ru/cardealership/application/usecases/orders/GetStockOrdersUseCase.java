package peipo.ru.cardealership.application.usecases.orders;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.repository.StockOrderRepository;

@AllArgsConstructor
public class GetStockOrdersUseCase
{
    private StockOrderRepository stockOrderRepository;

    public List<StockCarOrder> execute(Specification<StockCarOrder> spec)
    {
        return stockOrderRepository.findAll(spec);
    }
}
