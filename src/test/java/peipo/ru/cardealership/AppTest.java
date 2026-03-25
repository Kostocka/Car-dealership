package peipo.ru.cardealership;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.exception.EntityNotFoundException;
import peipo.ru.cardealership.domain.exception.IncompatibleComponentException;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.models.parts.Interior;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.models.specifications.AndSpecification;
import peipo.ru.cardealership.domain.models.specifications.NotSpecification;
import peipo.ru.cardealership.domain.models.specifications.OrSpecification;
import peipo.ru.cardealership.domain.models.specifications.Specification;
import peipo.ru.cardealership.domain.models.specifications.parts.*;
import peipo.ru.cardealership.domain.vo.BodyType;
import peipo.ru.cardealership.domain.vo.FuelType;
import peipo.ru.cardealership.domain.vo.GearBoxType;
import peipo.ru.cardealership.domain.vo.Money;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    private TestSystem system;

    @BeforeEach
    public void setUp()
    {
        system = new TestSystem();
    }

    @Test
    public void CreateStockOrder()
    {
        //Arrange
        Car audi =  system.carCatalogServices.find(new EnginePowerSpecification(600)).getFirst();
        ClientId testId = ClientId.generate();

        //Act
        StockCarOrder  order = system.orderService.createStockOrder(testId, audi.getCarId());

        // Assert
        assertNotNull(order);
        assertNotNull(order.getOrderId());
        assertFalse(system.stockOrderRepository.findByClientId(testId).isEmpty());
    }

    @Test
    public void CalculatePrice()
    {
        //Arrange
        CarModel audi =  system.carCatalogServices.find(new EnginePowerSpecification(600)).getFirst().getConfiguration();

        //Act
        var price = system.carPriceCalculatorService.calculate(audi);

        // Assert
        assertNotNull(price);
        assertTrue(price.value().compareTo(BigDecimal.ZERO) > 0 );
    }

    @Test
    public void RegisterTestDrive()
    {
        //Arrange
        Car audi =  system.carCatalogServices.find(new EnginePowerSpecification(600)).getFirst();
        ClientId testId = ClientId.generate();

        //Act
        var testdrive = system.testDriveService.signUp(testId, audi.getCarId());

        // Assert
        assertNotNull( testdrive);
        assertFalse(system.testDriveRepository.findByClientId(testId).isEmpty());
    }

    @Test
    public void TestDriveNotHaveCar()
    {
        //Arrange
        Car audi = system.carCatalogServices.find(new EnginePowerSpecification(600) ).getFirst();
        ClientId testId = ClientId.generate();

        //Act
        system.testCarsService.removeCar(audi.getCarId());

        // Assert
        assertThrows(DomainValidationException.class, () -> system.testDriveService.signUp(testId, audi.getCarId()) );
    }

    @Test
    public void CreateWrongConfiguration()
    {
        //Arrange
        CarModel BMW =   system.carModelCatalogServices.find(new ModelSpecification("320i")).getFirst();
        ClientId testId = ClientId.generate();
        Engine engine = system.engineRepository.findAll().stream().filter
                (e -> e.getPower().horsePower() == 600).findFirst().orElseThrow();

        //Act
        CarModel myConfiguration =  BMW.toBuilder().engine(engine).build();

        // Assert
        assertThrows(IncompatibleComponentException.class, () -> system.orderService.createConfiguredOrder(testId, myConfiguration));
        assertThrows(EntityNotFoundException.class, () -> system.orderService.createStockOrder(testId, CarId.generate()));
    }

    @Test
    public void CreateConfiguredOrder()
    {
        //Arrange
        CarModel BMW = system.carModelCatalogServices.find(new ModelSpecification("320i")).getFirst();
        ClientId testId = ClientId.generate();

        Wheels wheels19 = system.wheelsRepository.findAll().stream().filter(
                w -> w.getSize() == 19
        ).findFirst().orElseThrow();

        Interior dakota = system.interiorRepository.findAll().stream().filter(
                i -> i.getMaterial().equals("Dakota")
        ).findFirst().orElseThrow();

        //Act
        CarModel myConfiguration =  BMW.toBuilder().wheels(wheels19).interior(dakota).build();

        ConfiguredCarOrder baseOrder = system.orderService.createConfiguredOrder(testId, BMW);
        ConfiguredCarOrder confOrder = system.orderService.createConfiguredOrder(testId, myConfiguration);

        Money basePrice = system.carPriceCalculatorService.calculate(BMW);
        Money confPrice = system.carPriceCalculatorService.calculate(myConfiguration);

        // Assert
        assertNotNull(baseOrder);
        assertNotNull(confOrder);
        assertEquals(new Money(new BigDecimal(205000)), confPrice.sub(basePrice));
    }

    @Test
    public void Specifications()
    {
        //Arrange
        Specification<CarModel> audi = new AndSpecification<>(
                List.of(
                        new ColorSpecification(Color.DARK_GRAY),
                        new EngineValueSpecification(4),
                        new GearBoxSpecification(GearBoxType.MANUAL),
                        new NotSpecification<>(new BrandSpecification("BMW"))
                )
        );

        Specification<CarModel> BMW = new AndSpecification<>(
                List.of(
                        new NotSpecification<>(new BodySpecification(BodyType.LIMOUSINE)),
                        new FuelTypeSpecification(FuelType.GASOLINE),
                        new EnginePowerSpecification(200),
                        new NotSpecification<>(new BrandSpecification("Audi"))
                )
        );

        Specification<CarModel> spec = new OrSpecification<>(
                List.of(audi,BMW)
        );

        // Assert
        assertEquals(2, system.carModelCatalogServices.find(spec).size());
    }

    @Test
    public void AllStatesOrders()
    {
        //Arrange
        CarModel audi =  system.carModelCatalogServices.find(new ModelSpecification("RS6")).getFirst();
        ClientId testId = ClientId.generate();
        ConfiguredCarOrder order = system.orderService.createConfiguredOrder(testId, audi);


        //Act
        order.approve();
        order.pay();
        order.deliver();
        order.finish();

        Car car = system.carCatalogServices.find(new ModelSpecification("RS6")).getFirst();
        StockCarOrder stockCarOrder = system.orderService.createStockOrder(testId, car.getCarId());
        stockCarOrder.approve();
        stockCarOrder.pay();
        stockCarOrder.finish();

        // Assert
        assertThrows(DomainValidationException.class, stockCarOrder::approve);
        assertFalse(system.configuredOrderRepository.findByClientId(testId).isEmpty());
    }

}