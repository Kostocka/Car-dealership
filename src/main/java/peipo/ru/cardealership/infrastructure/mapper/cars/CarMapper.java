package peipo.ru.cardealership.infrastructure.mapper.cars;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarModelEntity;

@Mapper(componentModel = "spring", uses = CarModelMapper.class)
public abstract class CarMapper
{
    public Car toDomain(CarEntity entity)
    {
        return new Car(
                new CarId(entity.getId()),
                map(entity.getCarModel())
        );
    }

    public CarEntity toEntity(Car model)
    {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarModel(map(model.getConfiguration()));
        carEntity.setId(model.getCarId().id());
        return carEntity;
    }

    protected abstract CarModel map(CarModelEntity entity);

    protected abstract CarModelEntity map(CarModel model);
}
