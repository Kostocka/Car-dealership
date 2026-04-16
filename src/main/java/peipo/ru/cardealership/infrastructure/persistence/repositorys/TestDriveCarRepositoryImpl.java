package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.repository.TestDriveCarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.infrastructure.persistence.entity.testdrive.TestDriveCarEntity;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.TestDriveCarJpaRepository;

@Repository
@RequiredArgsConstructor
public class TestDriveCarRepositoryImpl implements TestDriveCarRepository
{
    private final TestDriveCarJpaRepository testDriveCarJpaRepository;

    @Override
    public void addCar(CarId carId)
    {
        TestDriveCarEntity testDriveCarEntity = new TestDriveCarEntity(carId.id());
        testDriveCarJpaRepository.save(testDriveCarEntity);
    }

    @Override
    public void removeCar(CarId carId)
    {
        testDriveCarJpaRepository.deleteById(carId.id());
    }

    @Override
    public boolean isAvailable(CarId carId)
    {
        return testDriveCarJpaRepository.existsById(carId.id());
    }
}
