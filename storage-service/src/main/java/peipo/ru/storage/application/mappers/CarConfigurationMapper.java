package peipo.ru.storage.application.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.common.vo.CarConfiguration;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.parts.*;
import peipo.ru.storage.domain.repository.CarModelRepository;
import peipo.ru.storage.domain.repository.PartRepository;
import peipo.ru.storage.domain.vo.CarModelId;

@Component
@RequiredArgsConstructor
public class CarConfigurationMapper
{
    private final CarModelRepository carModelRepository;

    private final PartRepository<Body> bodyRepository;
    private final PartRepository<Engine> engineRepository;
    private final PartRepository<GearBox> gearBoxRepository;
    private final PartRepository<Interior> interiorRepository;
    private final PartRepository<Wheels> wheelsRepository;

    public CarModel toDomain(CarConfiguration config)
    {
        CarModelId modelId = carModelRepository
                .findIdByBrandAndModel(config.getBrand(), config.getModel()).orElseThrow(
                    () -> new EntityNotFoundException(
                            "CarModel with id " + config.getBrand() + " " + config.getModel() + " not found")
                );

        Body body = bodyRepository.findById(config.getBody()).orElseThrow(
                () -> new EntityNotFoundException("body not found")
        );
        Engine engine = engineRepository.findById(config.getEngine()).orElseThrow(
                () -> new EntityNotFoundException("engine not found")
        );
        GearBox gearBox = gearBoxRepository.findById(config.getGearBox()).orElseThrow(
                () -> new EntityNotFoundException("gearbox not found")
        );
        Interior interior = interiorRepository.findById(config.getInterior()).orElseThrow(
                () -> new EntityNotFoundException("interior not found")
        );
        Wheels wheels = wheelsRepository.findById(config.getWheels()).orElseThrow(
                () -> new EntityNotFoundException("wheels not found")
        );

        return new CarModel(
                modelId,
                config.getBrand(),
                config.getModel(),
                body,
                engine,
                gearBox,
                interior,
                wheels,
                config.getDrivetrainType(),
                config.getColor()
        );
    }
}
