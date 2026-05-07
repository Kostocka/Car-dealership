package peipo.ru.storage.application.services;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peipo.ru.common.contracts.events.EventBus;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderRejectedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarReadyForPickupEvent;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.AssemblyStatus;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.models.StockAssemblyOrder;
import peipo.ru.storage.domain.repository.CarRepository;
import peipo.ru.storage.domain.repository.StockAssemblyOrderRepository;

@Service
@AllArgsConstructor
public class StockAssemblyOrderService
{
    private final CarRepository carRepository;
    private final StockAssemblyOrderRepository assemblyOrderRepository;
    private final InventoryService inventoryService;
    private final EventBus eventBus;

    @Transactional
    public void start(OrderId orderId, CarId carId)
    {
        try
        {
            Car car = carRepository.findById(carId)
                    .orElseThrow(
                            () -> new EntityNotFoundException("Car not found with id " + carId)
                    );

            StockAssemblyOrder assembly = new StockAssemblyOrder(
                    UUID.randomUUID(),
                    orderId,
                    Instant.now(),
                    Instant.now(),
                    AssemblyStatus.CREATED,
                    false,
                    carId
            );

            assemblyOrderRepository.save(assembly);

            inventoryService.reserveCar(carId, orderId);

            assembly.markAssembled();
            assemblyOrderRepository.save(assembly);

            eventBus.publish(new StockCarOrderAcceptedEvent(orderId));
        } catch (Exception e)
        {
            eventBus.publish(new StockCarOrderRejectedEvent(orderId, e.getMessage()));
        }
    }

    @Transactional
    public void completeDelivery(OrderId orderId)
    {
        StockAssemblyOrder assembly = assemblyOrderRepository.findByOrderId(orderId)
                .orElseThrow();

        assembly.markDelivered();
        assemblyOrderRepository.save(assembly);

        eventBus.publish(new StockCarReadyForPickupEvent(orderId));
    }

    @Transactional
    public void cancel(OrderId orderId)
    {
        StockAssemblyOrder assembly = assemblyOrderRepository.findByOrderId(orderId)
                .orElseThrow();

        inventoryService.releaseReservation(orderId);

        assembly.markCancelled();
        assemblyOrderRepository.save(assembly);
    }
}
