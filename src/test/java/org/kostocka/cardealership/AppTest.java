package org.kostocka.cardealership;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kostocka.cardealership.domain.exception.DomainValidationException;
import org.kostocka.cardealership.domain.exception.EntityNotFoundException;
import org.kostocka.cardealership.domain.exception.IncompatibleComponentException;
import org.kostocka.cardealership.domain.models.Car;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.orders.ConfiguredCarOrder;
import org.kostocka.cardealership.domain.models.orders.StockCarOrder;
import org.kostocka.cardealership.domain.models.parts.Engine;
import org.kostocka.cardealership.domain.models.parts.Interior;
import org.kostocka.cardealership.domain.models.parts.Wheels;
import org.kostocka.cardealership.domain.models.specifications.AndSpecification;
import org.kostocka.cardealership.domain.models.specifications.NotSpecification;
import org.kostocka.cardealership.domain.models.specifications.OrSpecification;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.models.specifications.parts.*;
import org.kostocka.cardealership.domain.vo.BodyType;
import org.kostocka.cardealership.domain.vo.FuelType;
import org.kostocka.cardealership.domain.vo.GearBoxType;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.CarId;
import org.kostocka.cardealership.domain.vo.id.ClientId;

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
        Car audi =  system.carCatalogServices.find(new EnginePowerSpecification(600)).getFirst();
        ClientId testId = ClientId.generate();

        StockCarOrder  order = system.orderService.createStockOrder(testId, audi.getCarId());

        assertNotNull(order);
        assertNotNull(order.getOrderId());
        assertFalse(system.stockOrderRepository.findByClientId(testId).isEmpty());
    }

    @Test
    public void CalculatePrice()
    {
        CarModel audi =  system.carCatalogServices.find(new EnginePowerSpecification(600)).getFirst().getConfiguration();
        var price = system.carPriceCalculatorService.calculate(audi);
        assertNotNull(price);
        assertTrue(price.value().compareTo(BigDecimal.ZERO) > 0 );
    }

    @Test
    public void RegisterTestDrive()
    {
        Car audi =  system.carCatalogServices.find(new EnginePowerSpecification(600)).getFirst();
        ClientId testId = ClientId.generate();
        var testdrive = system.testDriveService.signUp(testId, audi.getCarId());

        assertNotNull( testdrive);
        assertFalse(system.testDriveRepository.findByClientId(testId).isEmpty());
    }

    @Test
    public void TestDriveNotHaveCar()
    {
        Car audi = system.carCatalogServices.find(new EnginePowerSpecification(600) ).getFirst();
        ClientId testId = ClientId.generate();

        system.testCarsService.removeCar(audi.getCarId());

        assertThrows(DomainValidationException.class, () -> system.testDriveService.signUp(testId, audi.getCarId()) );
    }

    @Test
    public void CreateWrongConfiguration()
    {
        CarModel BMW =   system.carModelCatalogServices.find(new ModelSpecification("320i")).getFirst();
        ClientId testId = ClientId.generate();

        Engine engine = system.engineRepository.findAll().stream().filter
                (e -> e.getPower().horsePower() == 600).findFirst().orElseThrow();

        CarModel myConfiguration =  BMW.toBuilder().engine(engine).build();

        assertThrows(IncompatibleComponentException.class, () -> system.orderService.createConfiguredOrder(testId, myConfiguration));

        assertThrows(EntityNotFoundException.class, () -> system.orderService.createStockOrder(testId, CarId.generate()));
    }

    @Test
    public void CreateConfiguredOrder()
    {
        CarModel BMW = system.carModelCatalogServices.find(new ModelSpecification("320i")).getFirst();
        ClientId testId = ClientId.generate();

        Wheels wheels19 = system.wheelsRepository.findAll().stream().filter(
                w -> w.getSize() == 19
        ).findFirst().orElseThrow();

        Interior dakota = system.interiorRepository.findAll().stream().filter(
                i -> i.getMaterial().equals("Dakota")
        ).findFirst().orElseThrow();

        CarModel myConfiguration =  BMW.toBuilder().wheels(wheels19).interior(dakota).build();

        ConfiguredCarOrder baseOrder = system.orderService.createConfiguredOrder(testId, BMW);
        ConfiguredCarOrder confOrder = system.orderService.createConfiguredOrder(testId, myConfiguration);

        assertNotNull(baseOrder);
        assertNotNull(confOrder);

        Money basePrice = system.carPriceCalculatorService.calculate(BMW);
        Money confPrice = system.carPriceCalculatorService.calculate(myConfiguration);

        assertEquals(new Money(new BigDecimal(205000)), confPrice.sub(basePrice));
    }

    @Test
    public void Specifications()
    {
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

        assertEquals(2, system.carModelCatalogServices.find(spec).size());
    }

    @Test
    public void AllStatesOrders()
    {
        CarModel audi =  system.carModelCatalogServices.find(new ModelSpecification("RS6")).getFirst();
        ClientId testId = ClientId.generate();
        ConfiguredCarOrder order = system.orderService.createConfiguredOrder(testId, audi);

        order.approve();
        order.pay();
        order.deliver();
        order.finish();

        Car car = system.carCatalogServices.find(new ModelSpecification("RS6")).getFirst();
        StockCarOrder stockCarOrder = system.orderService.createStockOrder(testId, car.getCarId());
        stockCarOrder.approve();
        stockCarOrder.pay();
        stockCarOrder.finish();

        assertThrows(DomainValidationException.class, stockCarOrder::approve);
        assertFalse(system.configuredOrderRepository.findByClientId(testId).isEmpty());
    }

}