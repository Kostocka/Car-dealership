package peipo.ru.cardealership.infrastructure.persistence.mapper.cars;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarConfigurationEmbeddable;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarModelEntity;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.*;
import peipo.ru.cardealership.infrastructure.persistence.mapper.parts.*;

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
    @Autowired
    protected EngineMapper engineMapper;

    @Autowired
    protected BodyMapper bodyMapper;

    @Autowired
    protected GearBoxMapper gearBoxMapper;

    @Autowired
    protected InteriorMapper interiorMapper;

    @Autowired
    protected WheelsMapper wheelsMapper;

    public CarModel toDomain(CarModelEntity entity)
    {
        return new CarModel(
                new CarModelId(entity.getId()),
                entity.getBrand(),
                entity.getModel(),
                bodyMapper.toDomain(entity.getBody()),
                engineMapper.toDomain(entity.getEngine()),
                gearBoxMapper.toDomain(entity.getGearBox()),
                interiorMapper.toDomain(entity.getInterior()),
                wheelsMapper.toDomain(entity.getWheels()),
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
        carModelEntity.setBody(bodyMapper.toEntity(domain.getBody()));
        carModelEntity.setEngine(engineMapper.toEntity(domain.getEngine()));
        carModelEntity.setGearBox(gearBoxMapper.toEntity(domain.getGearBox()));
        carModelEntity.setInterior(interiorMapper.toEntity(domain.getInterior()));
        carModelEntity.setWheels(wheelsMapper.toEntity(domain.getWheels()));
        carModelEntity.setColor(domain.getColor().toString());
        carModelEntity.setDrivetrainType(domain.getDrivetrainType());
        return carModelEntity;
    }

    public CarModel toDomain(CarConfigurationEmbeddable entity)
    {
        return new CarModel(
                null,
                entity.getBrand(),
                entity.getModel(),
                bodyMapper.toDomain(entity.getBody()),
                engineMapper.toDomain(entity.getEngine()),
                gearBoxMapper.toDomain(entity.getGearBox()),
                interiorMapper.toDomain(entity.getInterior()),
                wheelsMapper.toDomain(entity.getWheels()),
                entity.getDrivetrainType(),
                Color.decode(entity.getColor())
        );
    }

    public CarConfigurationEmbeddable toEmbeddable(CarModel domain)
    {
        CarConfigurationEmbeddable emb = new CarConfigurationEmbeddable();
        emb.setBrand(domain.getBrand());
        emb.setModel(domain.getModel());
        emb.setBody(bodyMapper.toEntity(domain.getBody()));
        emb.setEngine(engineMapper.toEntity(domain.getEngine()));
        emb.setGearBox(gearBoxMapper.toEntity(domain.getGearBox()));
        emb.setInterior(interiorMapper.toEntity(domain.getInterior()));
        emb.setWheels(wheelsMapper.toEntity(domain.getWheels()));
        emb.setColor(domain.getColor().toString());
        emb.setDrivetrainType(domain.getDrivetrainType());
        return emb;
    }
}
