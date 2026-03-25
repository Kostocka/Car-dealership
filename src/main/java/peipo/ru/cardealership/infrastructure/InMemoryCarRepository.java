package peipo.ru.cardealership.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.repository.CarRepository;
import peipo.ru.cardealership.domain.vo.id.CarId;

public class InMemoryCarRepository implements CarRepository
{
    private final Map<CarId, Car> models = new HashMap<>();

    @Override
    public Optional<Car> findById(CarId carId)
    {
        Car model = models.get(carId);
        return model != null ? Optional.of(model) : Optional.empty();
    }

    @Override
    public List<Car> findAll()
    {
        return models.values()
                .stream()
                .toList();
    }

    @Override
    public void save(Car entity)
    {
        models.put(entity.getCarId(), entity);
    }

    @Override
    public void delete(CarId carId)
    {
        models.remove(carId);
    }
}