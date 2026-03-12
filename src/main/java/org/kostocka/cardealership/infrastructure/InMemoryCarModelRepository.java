package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.repository.CarModelRepository;
import org.kostocka.cardealership.domain.vo.id.CarModelId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCarModelRepository implements CarModelRepository
{
    private final Map<CarModelId, CarModel> models = new HashMap<>();

    @Override
    public List<CarModel> find(Specification<CarModel> spec) {
        return models.values().stream().filter(spec::isSatisfiedBy).map(CarModel::clone).toList();
    }

    @Override
    public Optional<CarModel> findById(CarModelId carModelId) {
        CarModel model = models.get(carModelId);
        return model != null ? Optional.of(model.clone()) : Optional.empty();
    }

    @Override
    public List<CarModel> findAll() {
        return models.values().stream().map(CarModel::clone).toList();
    }

    @Override
    public void save(CarModel entity) {
        models.put(entity.getModelId(), entity);
    }

    @Override
    public void delete(CarModelId carModelId) {
        models.remove(carModelId);
    }
}
