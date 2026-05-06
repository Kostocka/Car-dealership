package peipo.ru.storage.application.listeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderCreatedEvent;
import peipo.ru.storage.application.mappers.CarConfigurationMapper;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.services.AssemblyOrderService;

@Component
@AllArgsConstructor
public class ConfiguredOrderListener
{
    private final AssemblyOrderService assemblyOrderService;
    private final CarConfigurationMapper mapper;

    @EventListener
    public void handle(ConfiguredOrderCreatedEvent event)
    {
        CarModel model = mapper.toDomain(event.getConfiguration());

        assemblyOrderService.start(event.getOrderId(), model);
    }
}
