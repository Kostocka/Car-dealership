package peipo.ru.order.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.order.domain.models.TestDrive;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.order.domain.vo.id.TestDriveId;
import peipo.ru.order.infrastructure.persistence.entity.testdrive.TestDriveEntity;

@Mapper(componentModel = "spring")
public abstract class TestDriveMapper
{
    public TestDrive toDomain(TestDriveEntity entity)
    {
        return new TestDrive(
                new TestDriveId(entity.getId()),
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
        testDriveEntity.setId(testDriveEntity.getId());
        testDriveEntity.setStartTime(testDriveEntity.getStartTime());
        return testDriveEntity;
    }
}
