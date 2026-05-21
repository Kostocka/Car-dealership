package peipo.ru.storage.application.services;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peipo.ru.common.contracts.events.EventBus;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderRejectedEvent;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.application.mappers.CarConfigurationMapper;
import peipo.ru.storage.domain.AssemblyStatus;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.ConfiguredAssemblyOrder;
import peipo.ru.storage.domain.repository.ConfiguredAssemblyOrderRepository;
import peipo.ru.storage.domain.services.ConfiguratorService;

@Service
@AllArgsConstructor
public class ConfiguredAssemblyOrderService
{
    private final ConfiguratorService configuratorService;
    private final InventoryService inventoryService;
    private final ConfiguredAssemblyOrderRepository assemblyOrderRepository;
    private final EventBus eventBus;
    private final CarConfigurationMapper mapper;

    @Transactional
    public void start(OrderId orderId, CarConfiguration configuration)
    {
        ConfiguredAssemblyOrder assembly = new ConfiguredAssemblyOrder(
                UUID.randomUUID(),
                orderId,
                Instant.now(),
                Instant.now(),
                AssemblyStatus.CREATED,
                false,
                configuration
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
        ConfiguredAssemblyOrder assembly =
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
        ConfiguredAssemblyOrder assembly =
                assemblyOrderRepository.findByOrderId(orderId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "Assembly order not found"
                                )
                        );

        assembly.markDelivered();

        assemblyOrderRepository.save(assembly);
    }

    @Transactional
    public void cancel(OrderId orderId)
    {
        ConfiguredAssemblyOrder assembly = assemblyOrderRepository.findByOrderId(orderId).orElseThrow(
                () -> new EntityNotFoundException("Assembly order with order id " + orderId + " not found")
        );

        inventoryService.deReserveParts(assembly.getConfiguration());

        assembly.markCancelled();
        assemblyOrderRepository.save(assembly);
    }
}
