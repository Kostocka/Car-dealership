package org.kostocka.cardealership.infrastructure;


import org.kostocka.cardealership.domain.models.Car;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.repository.CarRepository;
import org.kostocka.cardealership.domain.vo.id.CarId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCarRepository implements CarRepository
{
    private final Map<CarId, Car> models = new HashMap<>();

    @Override
    public List<Car> find(Specification<Car> spec) {
        return models.values()
                .stream()
                .filter(spec::isSatisfiedBy)
                .toList();
    }

    @Override
    public Optional<Car> findById(CarId CarId) {
        Car model = models.get(CarId);
        return model != null ? Optional.of(model) : Optional.empty();
    }

    @Override
    public List<Car> findAll() {
        return models.values()
                .stream()
                .toList();
    }

    @Override
    public void save(Car entity) {
        models.put(entity.getCarId(), entity);
    }

    @Override
    public void delete(CarId CarId) {
        models.remove(CarId);
    }
}