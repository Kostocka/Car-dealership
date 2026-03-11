package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.vo.id.CarModelId;

import java.util.List;

public interface CarRepository extends Repository<CarModel, CarModelId>
{
    List<CarModel> find(Specification<CarModel> spec);
}
