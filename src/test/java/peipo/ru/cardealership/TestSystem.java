package peipo.ru.cardealership;

import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.employees.FirstAvailableEmployeeAssignmentStrategy;
import peipo.ru.cardealership.domain.models.parts.*;
import peipo.ru.cardealership.domain.repository.*;
import peipo.ru.cardealership.domain.services.*;
import peipo.ru.cardealership.domain.vo.*;
import peipo.ru.cardealership.domain.models.rules.CompatibilityRuleFactory;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.inMemory.*;

import java.awt.*;
import java.math.BigDecimal;

public class TestSystem
{
    public final CarPriceCalculatorService carPriceCalculatorService;
    public final CompatibilityService compatibilityService;
    public final ConfiguratorService configuratorService;
    public final EmployeeAssignmentService  employeeAssignmentService;
    public final OrderService orderService;
    public final TestCarsService testCarsService;
    public final TestDriveService testDriveService;
    public final CarCatalogServices carCatalogServices;
    public final CarModelCatalogServices carModelCatalogServices;

    public final CarModelRepository carModelRepository;
    public final CarRepository carRepository;
    public final ConfiguredOrderRepository configuredOrderRepository;
    public final EmployeeRepository  employeeRepository;
    public final ModelPartCompatibilityRepository modelPartCompatibilityRepository;
    public final PartCompatibilityRepository  partCompatibilityRepository;
    public final PartPriceRepository partPriceRepository;
    public final PartRepository<Body> bodyRepository;
    public final PartRepository<Engine> engineRepository;
    public final PartRepository<GearBox> gearBoxRepository;
    public final PartRepository<Interior> interiorRepository;
    public final PartRepository<Wheels>  wheelsRepository;
    public final StockOrderRepository  stockOrderRepository;
    public final TestDriveCarRepository  testDriveCarRepository;
    public final TestDriveRepository  testDriveRepository;

    public TestSystem(){
        carModelRepository = new InMemoryCarModelRepository();
        carRepository = new InMemoryCarRepository();
        configuredOrderRepository = new InMemoryConfiguredOrderRepository();
        employeeRepository = new InMemoryEmployeeRepository();
        modelPartCompatibilityRepository = new InMemoryModelPartCompatibilityRepository();
        partCompatibilityRepository = new InMemoryPartCompatibilityRepository();
        partPriceRepository = new InMemoryPartPriceRepository();
        bodyRepository = new InMemoryPartRepository<>();
        engineRepository = new InMemoryPartRepository<>();
        gearBoxRepository = new InMemoryPartRepository<>();
        interiorRepository = new InMemoryPartRepository<>();
        wheelsRepository = new InMemoryPartRepository<>();
        stockOrderRepository = new InMemoryStockOrderRepository();
        testDriveCarRepository = new InMemoryTestDriveCarRepository();
        testDriveRepository = new InMemoryTestDriveRepository();

        carPriceCalculatorService = new CarPriceCalculatorService(partPriceRepository);
        compatibilityService = new CompatibilityService(CompatibilityRuleFactory.createCompatibilityRules(modelPartCompatibilityRepository, partCompatibilityRepository));
        configuratorService = new ConfiguratorService(compatibilityService);
        employeeAssignmentService = new EmployeeAssignmentService(employeeRepository, new FirstAvailableEmployeeAssignmentStrategy());
        orderService = new OrderService(configuredOrderRepository, stockOrderRepository, carRepository, employeeAssignmentService,configuratorService);
        testCarsService = new TestCarsService(testDriveCarRepository);
        testDriveService = new TestDriveService(testDriveRepository, testDriveCarRepository);
        carCatalogServices = new CarCatalogServices(carRepository);
        carModelCatalogServices = new CarModelCatalogServices(carModelRepository);

        seedEmployees();
        seedParts();
        seedPrice();
        seedCars();
    }

    private void seedEmployees()
    {
        employeeRepository.save(EmployeeId.generate());
    }

    private void seedParts(){
        bodyRepository.save(new Body(PartId.generate(), BodyType.SEDAN));
        bodyRepository.save(new Body(PartId.generate(), BodyType.ESTATE));

        engineRepository.save(new Engine(PartId.generate(), FuelType.GASOLINE, new EnginePower(184), new EngineVolume(2)));
        engineRepository.save(new Engine(PartId.generate(), FuelType.GASOLINE, new EnginePower(258), new EngineVolume(3)));
        engineRepository.save(new Engine(PartId.generate(), FuelType.ELECTRIC, new EnginePower(100), new EngineVolume(3)));
        engineRepository.save(new Engine(PartId.generate(), FuelType.GASOLINE, new EnginePower(600), new EngineVolume(4)));


        gearBoxRepository.save(new GearBox(PartId.generate(), GearBoxType.AUTOMATIC));
        gearBoxRepository.save(new GearBox(PartId.generate(), GearBoxType.MANUAL));

        interiorRepository.save(new Interior(PartId.generate(), "Dakota"));
        interiorRepository.save(new Interior(PartId.generate(), "Graphite"));
        interiorRepository.save(new Interior(PartId.generate(), "Performance"));

        wheelsRepository.save(new Wheels(PartId.generate(), 17));
        wheelsRepository.save(new Wheels(PartId.generate(), 19));
    }

    private void seedPrice(){
        bodyRepository.findAll()
                .forEach(
                        body -> {
                            if(body.getType() ==  BodyType.SEDAN) {
                                partPriceRepository.setPartPrice(body.getId(), new Money(BigDecimal.valueOf(500000)));
                            }
                            if(body.getType() ==  BodyType.ESTATE) {
                                partPriceRepository.setPartPrice(body.getId(), new Money(BigDecimal.valueOf(550000)));
                            }
                        }
                );

        engineRepository.findAll()
                .forEach(
                        engine ->
                        {
                            if(engine.getFuelType() ==  FuelType.ELECTRIC) {
                                partPriceRepository.setPartPrice(engine.getId(), new Money(BigDecimal.valueOf(1400000)));
                            }
                            if(engine.getPower().horsePower() == 184) {
                                partPriceRepository.setPartPrice(engine.getId(), new Money(BigDecimal.valueOf(900000)));
                            }
                            if(engine.getPower().horsePower() == 258) {
                                partPriceRepository.setPartPrice(engine.getId(), new Money(BigDecimal.valueOf(1200000)));
                            }
                            if(engine.getPower().horsePower() == 600) {
                                partPriceRepository.setPartPrice(engine.getId(), new Money(BigDecimal.valueOf(3400000)));
                            }
                        }
                        );

        gearBoxRepository.findAll()
                .forEach(
                        gearBox ->
                        {
                            if(gearBox.getGearBoxType() ==  GearBoxType.AUTOMATIC) {
                                partPriceRepository.setPartPrice(gearBox.getId(), new Money(BigDecimal.valueOf(300000)));
                            }
                            if(gearBox.getGearBoxType() == GearBoxType.MANUAL) {
                                partPriceRepository.setPartPrice(gearBox.getId(), new Money(BigDecimal.valueOf(200000)));
                            }
                        }
                );

        interiorRepository.findAll()
                .forEach(
                        interior ->
                        {
                            if(interior.getMaterial().equals("Dakota"))
                            {
                                partPriceRepository.setPartPrice(interior.getId(), new Money(BigDecimal.valueOf(180000)));
                            }
                            if(interior.getMaterial().equals("Graphite"))
                            {
                                partPriceRepository.setPartPrice(interior.getId(), new Money(BigDecimal.valueOf(70000)));
                            }
                            if(interior.getMaterial().equals("Performance"))
                            {
                                partPriceRepository.setPartPrice(interior.getId(), new Money(BigDecimal.valueOf(230000)));
                            }
                        }
                );

        wheelsRepository.findAll()
                .forEach(
                        wheels ->
                        {
                            if(wheels.getSize() == 17)
                            {
                                partPriceRepository.setPartPrice(wheels.getId(), new Money(BigDecimal.valueOf(40000)));
                            }
                            if(wheels.getSize() == 19)
                            {
                                partPriceRepository.setPartPrice(wheels.getId(), new Money(BigDecimal.valueOf(135000)));
                            }
                        }
                );
    }

    private void seedCars()
    {
        Body sedan = bodyRepository.findAll().stream()
                .filter( b -> b.getType() == BodyType.SEDAN)
                .findFirst()
                .orElseThrow();

        Body estate = bodyRepository.findAll().stream()
                .filter( b -> b.getType() == BodyType.ESTATE)
                .findFirst()
                .orElseThrow();

        Engine engine184 = engineRepository.findAll().stream()
                .filter(e -> e.getPower().horsePower() == 184)
                .findFirst()
                .orElseThrow();

        Engine engine258 = engineRepository.findAll().stream()
                .filter( e -> e.getPower().horsePower() == 258)
                .findFirst()
                .orElseThrow();

        Engine engine600 = engineRepository.findAll().stream()
                .filter(e -> e.getPower().horsePower() == 600)
                .findFirst()
                .orElseThrow();

        GearBox autoGearBox = gearBoxRepository.findAll().stream()
                .filter(e -> e.getGearBoxType() == GearBoxType.AUTOMATIC)
                .findFirst()
                .orElseThrow();

        GearBox manualGearBox = gearBoxRepository.findAll().stream()
                .filter(e -> e.getGearBoxType() == GearBoxType.MANUAL)
                .findFirst()
                .orElseThrow();

        Interior graphite = interiorRepository.findAll().stream()
                .filter( i -> i.getMaterial().equals("Graphite"))
                .findFirst()
                .orElseThrow();

        Interior performance = interiorRepository.findAll().stream()
                .filter( i -> i.getMaterial().equals("Performance"))
                .findFirst()
                .orElseThrow();

        Interior dakota = interiorRepository.findAll().stream()
                .filter( i -> i.getMaterial().equals("Dakota"))
                .findFirst()
                .orElseThrow();

        Wheels wheels17= wheelsRepository.findAll().stream()
                .filter( w -> w.getSize() == 17)
                .findFirst()
                .orElseThrow();

        Wheels wheels19= wheelsRepository.findAll().stream()
                .filter( w -> w.getSize() == 19)
                .findFirst()
                .orElseThrow();

        CarModel BMW320i = new CarModel(
                CarModelId.generate(),
                "BMW",
                "320i",
                sedan,
                engine184,
                autoGearBox,
                graphite,
                wheels17,
                DrivetrainType.FourWD,
                Color.BLACK
        );

        CarModel BMW330i = new CarModel(
                CarModelId.generate(),
                "BMW",
                "330i",
                sedan,
                engine258,
                autoGearBox,
                graphite,
                wheels17,
                DrivetrainType.FourWD,
                Color.BLACK
        );

        CarModel AudiRS6 = new CarModel(
                CarModelId.generate(),
                "Audi",
                "RS6",
                estate,
                engine600,
                manualGearBox,
                performance,
                wheels19,
                DrivetrainType.FourWD,
                Color.DARK_GRAY
        );

        carModelRepository.save(BMW320i);
        carModelRepository.save(AudiRS6);
        carModelRepository.save(BMW330i);

        Car AudiRS = new Car(
                CarId.generate(),
                AudiRS6
        );

        carRepository.save(AudiRS);
        testCarsService.addCar(AudiRS.getCarId());

        modelPartCompatibilityRepository.add(sedan.getId(),BMW320i.getModelId());
        modelPartCompatibilityRepository.add(engine184.getId(),BMW320i.getModelId());
        modelPartCompatibilityRepository.add(autoGearBox.getId(),BMW320i.getModelId());
        modelPartCompatibilityRepository.add(graphite.getId(),BMW320i.getModelId());
        modelPartCompatibilityRepository.add(dakota.getId(),BMW320i.getModelId());
        modelPartCompatibilityRepository.add(wheels17.getId(),BMW320i.getModelId());
        modelPartCompatibilityRepository.add(wheels19.getId(),BMW320i.getModelId());

        modelPartCompatibilityRepository.add(sedan.getId(),BMW330i.getModelId());
        modelPartCompatibilityRepository.add(engine258.getId(),BMW330i.getModelId());
        modelPartCompatibilityRepository.add(autoGearBox.getId(),BMW330i.getModelId());
        modelPartCompatibilityRepository.add(graphite.getId(),BMW330i.getModelId());
        modelPartCompatibilityRepository.add(dakota.getId(),BMW330i.getModelId());
        modelPartCompatibilityRepository.add(wheels19.getId(),BMW330i.getModelId());

        modelPartCompatibilityRepository.add(estate.getId(),AudiRS6.getModelId());
        modelPartCompatibilityRepository.add(performance.getId(),AudiRS6.getModelId());
        modelPartCompatibilityRepository.add(wheels19.getId(),AudiRS6.getModelId());
        modelPartCompatibilityRepository.add(manualGearBox.getId(),AudiRS6.getModelId());
        modelPartCompatibilityRepository.add(engine600.getId(),AudiRS6.getModelId());

        partCompatibilityRepository.addCompatibility(engine600.getId(), manualGearBox.getId());
        partCompatibilityRepository.addCompatibility(engine184.getId(), autoGearBox.getId());
        partCompatibilityRepository.addCompatibility(engine258.getId(), autoGearBox.getId());
    }
}
