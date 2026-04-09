package peipo.ru.cardealership;

import jakarta.transaction.Transactional;
import java.awt.*;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.application.usecases.models.AddCarModelUseCase;
import peipo.ru.cardealership.application.usecases.models.AddCarUseCase;
import peipo.ru.cardealership.application.usecases.parts.*;
import peipo.ru.cardealership.application.usecases.testdrives.AddCarToTestDrive;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.parts.*;
import peipo.ru.cardealership.domain.vo.*;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.PartId;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder
{
    private final CreateBodyUseCase bodyCreatePartUseCase;
    private final CreateEngineUseCase engineCreatePartUseCase;
    private final CreateGearBoxUseCase gearBoxCreatePartUseCase;
    private final CreateInteriorUseCase interiorCreatePartUseCase;
    private final CreateWheelsUseCase wheelsCreatePartUseCase;

    private final SetPartPriceUseCase setPartPriceUseCase;
    private final AddPartCompatibilityUseCase addPartCompatibilityUseCase;
    private final AddModelPartCompatibilityUseCase addModelPartCompatibilityUseCase;

    private final AddCarModelUseCase addCarModelUseCase;
    private final AddCarUseCase addCarUseCase;
    private final AddCarToTestDrive addCarToTestDriveUseCase;

    @Autowired
    private final DatabaseCleaner cleaner;

    @Transactional
    public void seed()
    {
        cleaner.clean();
        Body sedan = new Body(PartId.generate(), BodyType.SEDAN);
        Body estate = new Body(PartId.generate(), BodyType.ESTATE);
        bodyCreatePartUseCase.execute(sedan);
        bodyCreatePartUseCase.execute(estate);


        Engine engine184 =
                new Engine(PartId.generate(), FuelType.GASOLINE, new EnginePower(184), new EngineVolume(2));
        Engine engine258 =
                new Engine(PartId.generate(), FuelType.GASOLINE, new EnginePower(258), new EngineVolume(3));
        Engine engine100 =
                new Engine(PartId.generate(), FuelType.ELECTRIC, new EnginePower(100), new EngineVolume(3));
        Engine engine600 =
                new Engine(PartId.generate(), FuelType.GASOLINE, new EnginePower(600), new EngineVolume(4));
        engineCreatePartUseCase.execute(engine184);
        engineCreatePartUseCase.execute(engine258);
        engineCreatePartUseCase.execute(engine100);
        engineCreatePartUseCase.execute(engine600);

        GearBox automatic = new GearBox(PartId.generate(), GearBoxType.AUTOMATIC);
        GearBox manual = new GearBox(PartId.generate(), GearBoxType.MANUAL);
        gearBoxCreatePartUseCase.execute(automatic);
        gearBoxCreatePartUseCase.execute(manual);

        Interior dakota = new Interior(PartId.generate(), "Dakota");
        Interior graphite = new Interior(PartId.generate(), "Graphite");
        Interior performance = new Interior(PartId.generate(), "Performance");
        interiorCreatePartUseCase.execute(dakota);
        interiorCreatePartUseCase.execute(graphite);
        interiorCreatePartUseCase.execute(performance);

        Wheels wheels17 = new Wheels(PartId.generate(), 17);
        Wheels wheels19 = new Wheels(PartId.generate(), 19);
        wheelsCreatePartUseCase.execute(wheels17);
        wheelsCreatePartUseCase.execute(wheels19);

        setPartPriceUseCase.execute(sedan.getId(), new Money(BigDecimal.valueOf(500000)));
        setPartPriceUseCase.execute(estate.getId(), new Money(BigDecimal.valueOf(550000)));

        setPartPriceUseCase.execute(engine184.getId(), new Money(BigDecimal.valueOf(900000)));
        setPartPriceUseCase.execute(engine258.getId(), new Money(BigDecimal.valueOf(1200000)));
        setPartPriceUseCase.execute(engine100.getId(), new Money(BigDecimal.valueOf(1400000)));
        setPartPriceUseCase.execute(engine600.getId(), new Money(BigDecimal.valueOf(3400000)));

        setPartPriceUseCase.execute(automatic.getId(), new Money(BigDecimal.valueOf(300000)));
        setPartPriceUseCase.execute(manual.getId(), new Money(BigDecimal.valueOf(200000)));

        setPartPriceUseCase.execute(dakota.getId(), new Money(BigDecimal.valueOf(180000)));
        setPartPriceUseCase.execute(graphite.getId(), new Money(BigDecimal.valueOf(70000)));
        setPartPriceUseCase.execute(performance.getId(), new Money(BigDecimal.valueOf(230000)));

        setPartPriceUseCase.execute(wheels17.getId(), new Money(BigDecimal.valueOf(40000)));
        setPartPriceUseCase.execute(wheels19.getId(), new Money(BigDecimal.valueOf(135000)));

        addPartCompatibilityUseCase.execute(engine184.getId(), automatic.getId());
        addPartCompatibilityUseCase.execute(engine258.getId(), automatic.getId());
        addPartCompatibilityUseCase.execute(engine600.getId(), manual.getId());

        CarModel bmw320i = new CarModel(
                CarModelId.generate(),
                "bmw",
                "320i",
                sedan,
                engine184,
                automatic,
                graphite,
                wheels17,
                DrivetrainType.FourWD,
                Color.BLACK.toString()
        );

        CarModel bmw330i = new CarModel(
                CarModelId.generate(),
                "bmw",
                "330i",
                sedan,
                engine258,
                automatic,
                graphite,
                wheels17,
                DrivetrainType.FourWD,
                Color.BLACK.toString()
        );

        CarModel audiRs6 = new CarModel(
                CarModelId.generate(),
                "Audi",
                "RS6",
                estate,
                engine600,
                manual,
                performance,
                wheels19,
                DrivetrainType.FourWD,
                Color.DARK_GRAY.toString()
        );

        addCarModelUseCase.execute(bmw320i);
        addCarModelUseCase.execute(bmw330i);
        addCarModelUseCase.execute(audiRs6);

        addModelPartCompatibilityUseCase.execute(sedan.getId(), bmw320i.getModelId());
        addModelPartCompatibilityUseCase.execute(engine184.getId(), bmw320i.getModelId());
        addModelPartCompatibilityUseCase.execute(automatic.getId(), bmw320i.getModelId());
        addModelPartCompatibilityUseCase.execute(graphite.getId(), bmw320i.getModelId());
        addModelPartCompatibilityUseCase.execute(dakota.getId(), bmw320i.getModelId());
        addModelPartCompatibilityUseCase.execute(wheels17.getId(), bmw320i.getModelId());
        addModelPartCompatibilityUseCase.execute(wheels19.getId(), bmw320i.getModelId());

        addModelPartCompatibilityUseCase.execute(sedan.getId(), bmw330i.getModelId());
        addModelPartCompatibilityUseCase.execute(engine258.getId(), bmw330i.getModelId());
        addModelPartCompatibilityUseCase.execute(automatic.getId(), bmw330i.getModelId());
        addModelPartCompatibilityUseCase.execute(graphite.getId(), bmw330i.getModelId());
        addModelPartCompatibilityUseCase.execute(dakota.getId(), bmw330i.getModelId());
        addModelPartCompatibilityUseCase.execute(wheels19.getId(), bmw330i.getModelId());

        addModelPartCompatibilityUseCase.execute(estate.getId(), audiRs6.getModelId());
        addModelPartCompatibilityUseCase.execute(performance.getId(), audiRs6.getModelId());
        addModelPartCompatibilityUseCase.execute(wheels19.getId(), audiRs6.getModelId());
        addModelPartCompatibilityUseCase.execute(manual.getId(), audiRs6.getModelId());
        addModelPartCompatibilityUseCase.execute(engine600.getId(), audiRs6.getModelId());

        Car audiRs = new Car(CarId.generate(), audiRs6);
        addCarUseCase.execute(audiRs);
        addCarToTestDriveUseCase.execute(audiRs.getCarId());
    }

}
