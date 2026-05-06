package peipo.ru.storage.domain.repository;

import java.util.Optional;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.vo.CarModelId;

public interface CarModelRepository extends Repository<CarModel, CarModelId>
{
    Optional<CarModelId> findIdByBrandAndModel(String brand, String model);
}