package peipo.ru.cardealership.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.parts.Part;
import peipo.ru.cardealership.domain.repository.PartStockRepository;

@Service
@AllArgsConstructor
public class InventoryService
{
    private PartStockRepository partStockRepository;

    private void checkPart(Part part)
    {
        if (partStockRepository.getQuantity(part.getId()) <= 0)
        {
            throw new DomainValidationException("Part not available");
        }
    }

    private void reservePart(Part part)
    {
        partStockRepository.decrease(part.getId(), 1);
    }

    public void checkAvailability(CarModel carModel)
    {
        checkPart(carModel.getBody());
        checkPart(carModel.getEngine());
        checkPart(carModel.getGearBox());
        checkPart(carModel.getInterior());
        checkPart(carModel.getWheels());
    }

    public void reserveParts(CarModel carModel)
    {
        reservePart(carModel.getBody());
        reservePart(carModel.getEngine());
        reservePart(carModel.getGearBox());
        reservePart(carModel.getInterior());
        reservePart(carModel.getWheels());
    }
}
