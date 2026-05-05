package peipo.ru.storage.infrastructure.persistence.mappers.cars;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.vo.CarModelId;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarConfigurationEmbeddable;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarModelEntity;
import peipo.ru.storage.infrastructure.persistence.mappers.parts.*;

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
                entity.getColor()
        );
    }

    public CarModel toDomain(CarConfigurationEmbeddable entity)
    {
        return new CarModel(
                new CarModelId(entity.getModelId()),
                entity.getBrand(),
                entity.getModel(),
                bodyMapper.toDomain(entity.getBody()),
                engineMapper.toDomain(entity.getEngine()),
                gearBoxMapper.toDomain(entity.getGearBox()),
                interiorMapper.toDomain(entity.getInterior()),
                wheelsMapper.toDomain(entity.getWheels()),
                entity.getDrivetrainType(),
                entity.getColor()
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

    public CarConfigurationEmbeddable toEmbeddable(CarModel domain)
    {
        CarConfigurationEmbeddable emb = new CarConfigurationEmbeddable();
        emb.setModelId(domain.getModelId().id());
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
