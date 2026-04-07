package peipo.ru.cardealership.application.usecases.orders;

import java.util.List;
import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.repository.StockOrderRepository;

@AllArgsConstructor
public class GetStockOrdersUseCase
{
    private StockOrderRepository stockOrderRepository;

    public List<StockCarOrder> execute()
    {
        return stockOrderRepository.findAll();
    }
}
