package peipo.ru.cardealership.infrastructure.mapper;

import org.mapstruct.Mapper;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.domain.vo.id.TestDriveId;
import peipo.ru.cardealership.infrastructure.persistence.entity.cars.CarEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.testdrive.TestDriveEntity;

@Mapper(componentModel = "spring")
public abstract class TestDriveMapper
{
    public TestDrive toDomain(TestDriveEntity entity)
    {
        return new TestDrive(
                new TestDriveId(entity.getTestDriveId()),
                new ClientId(entity.getClientId()),
                new CarId(entity.getCarId()),
                entity.getStartTime()
        );
    }

    public TestDriveEntity toEntity(TestDrive testDrive)
    {
        TestDriveEntity testDriveEntity = new TestDriveEntity();
        testDriveEntity.setCarId(testDriveEntity.getCarId());
        testDriveEntity.setClientId(testDriveEntity.getClientId());
        testDriveEntity.setTestDriveId(testDriveEntity.getTestDriveId());
        testDriveEntity.setStartTime(testDriveEntity.getStartTime());
        return testDriveEntity;
    }

    protected abstract CarEntity map(CarId carId);
}
