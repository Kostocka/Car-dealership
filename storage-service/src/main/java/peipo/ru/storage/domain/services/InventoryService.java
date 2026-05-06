package peipo.ru.storage.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.repository.PartStockRepository;

@Service
@AllArgsConstructor
public class InventoryService
{
    private PartStockRepository partStockRepository;

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
