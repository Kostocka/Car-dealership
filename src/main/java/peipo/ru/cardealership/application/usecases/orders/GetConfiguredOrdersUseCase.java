package peipo.ru.cardealership.application.usecases.orders;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.repository.ConfiguredOrderRepository;

@AllArgsConstructor
public class GetConfiguredOrdersUseCase
{
    private ConfiguredOrderRepository configuredOrderRepository;

    public List<ConfiguredCarOrder> execute(Specification<ConfiguredCarOrder> spec)
    {
        return configuredOrderRepository.findAll(spec);
    }
}
