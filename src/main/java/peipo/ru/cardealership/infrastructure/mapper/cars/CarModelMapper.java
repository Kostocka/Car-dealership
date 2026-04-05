package peipo.ru.cardealership.infrastructure.mapper.cars;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.infrastructure.mapper.parts.*;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarModelEntity;

import java.awt.*;

@Mapper(
        componentModel = "spring",
        uses =
        {
            EngineMapper.class,
            BodyMapper.class,
            GearBoxMapper.class,
            InteriorMapper.class,
            WheelsMapper.class
        }
)
public abstract class CarModelMapper
{
    public CarModel toDomain(CarModelEntity entity)
    {
        return new CarModel(
                new CarModelId(entity.getId()),
                entity.getBrand(),
                entity.getModel(),
                bodyMapper().toDomain(entity.getBody()),
                engineMapper().toDomain(entity.getEngine()),
                gearBoxMapper().toDomain(entity.getGearBox()),
                interiorMapper().toDomain(entity.getInterior()),
                wheelsMapper().toDomain(entity.getWheels()),
                entity.getDrivetrainType(),
                Color.decode(entity.getColor())
        );
    }

    public CarModelEntity toEntity(CarModel domain)
    {
        CarModelEntity carModelEntity = new CarModelEntity();
        carModelEntity.setId(domain.getModelId().id());
        carModelEntity.setBrand(domain.getBrand());
        carModelEntity.setModel(domain.getModel());
        carModelEntity.setBody(bodyMapper().toEntity(domain.getBody()));
        carModelEntity.setEngine(engineMapper().toEntity(domain.getEngine()));
        carModelEntity.setGearBox(gearBoxMapper().toEntity(domain.getGearBox()));
        carModelEntity.setInterior(interiorMapper().toEntity(domain.getInterior()));
        carModelEntity.setWheels(wheelsMapper().toEntity(domain.getWheels()));
        carModelEntity.setColor(domain.getColor().toString());
        carModelEntity.setDrivetrainType(domain.getDrivetrainType());
        return carModelEntity;
    }

    protected abstract EngineMapper engineMapper();

    protected abstract BodyMapper bodyMapper();

    protected abstract GearBoxMapper gearBoxMapper();

    protected abstract InteriorMapper interiorMapper();

    protected abstract WheelsMapper wheelsMapper();

}
