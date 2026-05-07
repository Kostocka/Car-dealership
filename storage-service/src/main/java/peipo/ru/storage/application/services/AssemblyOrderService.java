package peipo.ru.storage.application.services;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peipo.ru.common.contracts.events.EventBus;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderDeliveredEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderRejectedEvent;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.application.mappers.CarConfigurationMapper;
import peipo.ru.storage.domain.AssemblyStatus;
import peipo.ru.storage.domain.models.AssemblyOrder;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.AssemblyOrderRepository;
import peipo.ru.storage.domain.services.ConfiguratorService;
import peipo.ru.storage.domain.services.InventoryService;

@Service
@AllArgsConstructor
public class AssemblyOrderService
{
    private final ConfiguratorService configuratorService;
    private final InventoryService inventoryService;
    private final AssemblyOrderRepository assemblyOrderRepository;
    private final EventBus eventBus;
    private final CarConfigurationMapper mapper;

    @Transactional
    public void start(OrderId orderId, CarConfiguration configuration)
    {
        AssemblyOrder assembly = new AssemblyOrder(
                UUID.randomUUID(),
                orderId,
                configuration,
                Instant.now(),
                Instant.now(),
                AssemblyStatus.CREATED,
                false
        );

        assemblyOrderRepository.save(assembly);

        try
        {
            CarModel model = mapper.toDomain(configuration);

            configuratorService.validateConfiguration(model);
            inventoryService.reserveParts(configuration);

            assembly.markAssembled();
            assemblyOrderRepository.save(assembly);

            eventBus.publish(
                    new ConfiguredOrderAcceptedEvent(orderId)
            );

        } catch (Exception e)
        {
            assembly.markFailed();
            assemblyOrderRepository.save(assembly);

            eventBus.publish(
                    new ConfiguredOrderRejectedEvent(
                            orderId,
                            e.getMessage()
                    )
            );
        }
    }

    @Transactional
    public void startDelivery(OrderId orderId)
    {
        AssemblyOrder assembly =
                assemblyOrderRepository.findByOrderId(orderId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Assembly order not found"
                                )
                        );

        assembly.markDelivering();

        assemblyOrderRepository.save(assembly);
    }

    @Transactional
    public void completeDelivery(OrderId orderId)
    {
        AssemblyOrder assembly =
                assemblyOrderRepository.findByOrderId(orderId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Assembly order not found"
                                )
                        );

        assembly.markDelivered();

        assemblyOrderRepository.save(assembly);

        eventBus.publish(
                new ConfiguredOrderDeliveredEvent(orderId)
        );
    }

    @Transactional
    public void cancel(OrderId orderId)
    {
        AssemblyOrder assembly = assemblyOrderRepository.findByOrderId(orderId).orElseThrow(
                () -> new EntityNotFoundException("Assembly order with order id " + orderId + " not found")
        );

        inventoryService.deReserveParts(assembly.getConfiguration());

        assembly.markCancelled();
        assemblyOrderRepository.save(assembly);
    }
}
