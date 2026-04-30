package peipo.ru.storage.infrastructure.persistence.mappers.parts;

import java.util.UUID;
import org.mapstruct.Mapper;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.PartId;

@Mapper(componentModel = "spring")
public interface IdMapper
{
    default UUID map(CarId carId)
    {
        return carId.id();
    }

    default PartId map(UUID id)
    {
        return new PartId(id);
    }

    default UUID map(CarModelId carModelId)
    {
        return carModelId.id();
    }

    default UUID map(PartId id)
    {
        return id.id();
    }

    default CarId mapCarId(UUID carId)
    {
        return new CarId(carId);
    }

    default CarModelId mapCarModelId(UUID carModelId)
    {
        return new CarModelId(carModelId);
    }
}
