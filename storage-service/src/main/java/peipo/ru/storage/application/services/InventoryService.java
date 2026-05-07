package peipo.ru.storage.application.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.CarReservation;
import peipo.ru.storage.domain.models.ReservationStatus;
import peipo.ru.storage.domain.repository.CarReservationRepository;
import peipo.ru.storage.domain.repository.PartStockRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InventoryService
{
    private PartStockRepository partStockRepository;
    private CarReservationRepository carReservationRepository;

    public void reserveParts(CarConfiguration carConfiguration)
    {
        checkAvailability(carConfiguration);

        reservePart(carConfiguration.getBody());
        reservePart(carConfiguration.getEngine());
        reservePart(carConfiguration.getGearBox());
        reservePart(carConfiguration.getInterior());
        reservePart(carConfiguration.getWheels());
    }

    public void deReserveParts(CarConfiguration carConfiguration)
    {
        deReservePart(carConfiguration.getBody());
        deReservePart(carConfiguration.getEngine());
        deReservePart(carConfiguration.getGearBox());
        deReservePart(carConfiguration.getInterior());
        deReservePart(carConfiguration.getWheels());
    }

    public void reserveCar(CarId carId, OrderId orderId)
    {
        if (carReservationRepository.existsActiveByCarId(carId))
        {
            throw new DomainValidationException("Car already reserved");
        }

        CarReservation reservation = new CarReservation(
                UUID.randomUUID(),
                carId,
                orderId,
                Instant.now(),
                ReservationStatus.ACTIVE
        );

        carReservationRepository.save(reservation);
    }

    public void releaseReservation(OrderId orderId)
    {
        CarReservation reservation = carReservationRepository.findByOrderId(orderId)
                .orElseThrow();

        reservation.setStatus(ReservationStatus.RELEASED);
        carReservationRepository.save(reservation);
    }

    private void checkPart(PartId part)
    {
        if (partStockRepository.getQuantity(part) <= 0)
        {
            throw new DomainValidationException("Part not available " + part);
        }
    }

    private void reservePart(PartId part)
    {
        partStockRepository.decrease(part, 1);
    }

    private void deReservePart(PartId part)
    {
        partStockRepository.increase(part, 1);
    }

    private void checkAvailability(CarConfiguration carConfiguration)
    {
        checkPart(carConfiguration.getBody());
        checkPart(carConfiguration.getEngine());
        checkPart(carConfiguration.getGearBox());
        checkPart(carConfiguration.getInterior());
        checkPart(carConfiguration.getWheels());
    }
}
