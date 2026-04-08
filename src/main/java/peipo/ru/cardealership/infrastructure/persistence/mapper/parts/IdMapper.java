package peipo.ru.cardealership.infrastructure.persistence.mapper.parts;

import java.util.UUID;
import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.OrderId;
import peipo.ru.cardealership.domain.vo.id.PartId;

@Mapper(componentModel = "spring")
public interface IdMapper
{
    default UUID map(CarId carId)
    {
        return carId.id();
    }

    default CarId mapCarId(UUID carId)
    {
        return new CarId(carId);
    }

    default PartId map(UUID id)
    {
        return new PartId(id);
    }

    default UUID map(PartId id)
    {
        return id.id();
    }

    default OrderId mapOrderId(UUID id)
    {
        return new OrderId(id);
    }

    default UUID map(OrderId id)
    {
        return id.id();
    }

    default CarModelId mapCarModelId(UUID carModelId)
    {
        return new CarModelId(carModelId);
    }

    default UUID map(CarModelId carModelId)
    {
        return carModelId.id();
    }
}
