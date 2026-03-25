package peipo.ru.cardealership.infrastructure;

import java.util.ArrayList;
import java.util.List;
import peipo.ru.cardealership.domain.repository.TestDriveCarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;

public class InMemoryTestDriveCarRepository implements TestDriveCarRepository
{
    private final List<CarId> testCars = new ArrayList<>();

    @Override
    public void addCar(CarId carId)
    {
        this.testCars.add(carId);
    }

    @Override
    public void removeCar(CarId carId)
    {
        this.testCars.remove(carId);
    }

    @Override
    public boolean isAvailable(CarId carId)
    {
        return this.testCars.contains(carId);
    }

    @Override
    public List<CarId> findAll()
    {
        return this.testCars;
    }
}
