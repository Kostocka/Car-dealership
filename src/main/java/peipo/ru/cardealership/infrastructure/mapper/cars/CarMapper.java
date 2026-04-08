package peipo.ru.cardealership.infrastructure.mapper.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;

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