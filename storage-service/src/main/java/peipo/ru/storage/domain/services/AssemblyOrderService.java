package peipo.ru.storage.domain.services;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.contracts.events.EventBus;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderRejectedEvent;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.AssemblyStatus;
import peipo.ru.storage.domain.models.AssemblyOrder;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.AssemblyOrderRepository;

@Service
@AllArgsConstructor
public class AssemblyOrderService
{
    private final ConfiguratorService configuratorService;
    private final InventoryService inventoryService;
    private final AssemblyOrderRepository assemblyOrderRepository;
    private final EventBus eventBus;

    public void start(OrderId orderId, CarModel model)
    {
        AssemblyOrder assembly = new AssemblyOrder(
                UUID.randomUUID(),
                orderId,
                Instant.now(),
                Instant.now(),
                AssemblyStatus.CREATED,
                false
        );

        assemblyOrderRepository.save(assembly);

        try
        {
            configuratorService.validateConfiguration(model);
            inventoryService.checkAvailability(model);
            inventoryService.reserveParts(model);

            eventBus.publish(
                    new ConfiguredOrderAcceptedEvent(orderId)
            );
        } catch (Exception e)
        {
            eventBus.publish(
                    new ConfiguredOrderRejectedEvent(
                            orderId,
                            e.getMessage()
                    )
            );
        }
    }
}
