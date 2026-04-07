package peipo.ru.cardealership.application.usecases.orders;

import java.util.List;
import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.repository.ConfiguredOrderRepository;

@AllArgsConstructor
public class GetConfiguredOrdersUseCase
{
    private ConfiguredOrderRepository configuredOrderRepository;

    public List<ConfiguredCarOrder> execute()
    {
        return configuredOrderRepository.findAll();
    }
}
