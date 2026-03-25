package peipo.ru.cardealership.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.vo.id.CarModelId;

public interface CarModelRepository extends JpaRepository<CarModel, CarModelId>, JpaSpecificationExecutor<CarModel>
{
}