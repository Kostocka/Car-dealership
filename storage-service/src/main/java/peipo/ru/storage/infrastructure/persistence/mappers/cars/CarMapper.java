package peipo.ru.storage.infrastructure.persistence.mappers.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;

@Component
@RequiredArgsConstructor
public class CarMapper
{
    private final CarModelMapper carModelMapper;

    public Car toDomain(CarEntity entity)
    {
        return new Car(
                new CarId(entity.getId()),
                carModelMapper.toDomain(entity.getCarModel())
        );
    }

    public CarEntity toEntity(Car model)
    {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarModel(carModelMapper.toEmbeddable(model.getConfiguration()));
        carEntity.setId(model.getCarId().id());
        return carEntity;
    }
}