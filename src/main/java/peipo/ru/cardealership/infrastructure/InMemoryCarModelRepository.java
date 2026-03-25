package peipo.ru.cardealership.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.repository.CarModelRepository;
import peipo.ru.cardealership.domain.vo.id.CarModelId;

public class InMemoryCarModelRepository implements CarModelRepository
{
    private final Map<CarModelId, CarModel> models = new HashMap<>();

    @Override
    public Optional<CarModel> findById(CarModelId carModelId)
    {
        CarModel model = models.get(carModelId);
        return model != null ? Optional.of(model) : Optional.empty();
    }

    @Override
    public List<CarModel> findAll()
    {
        return models.values().stream().toList();
    }

    @Override
    public void save(CarModel entity)
    {
        models.put(entity.getModelId(), entity);
    }

    @Override
    public void delete(CarModelId carModelId)
    {
        models.remove(carModelId);
    }
}
