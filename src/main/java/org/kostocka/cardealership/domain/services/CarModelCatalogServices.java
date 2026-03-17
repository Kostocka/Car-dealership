package org.kostocka.cardealership.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.CarModel;
import org.kostocka.cardealership.domain.models.specifications.Specification;
import org.kostocka.cardealership.domain.repository.CarModelRepository;

@AllArgsConstructor
public class CarModelCatalogServices
{
    private CarModelRepository carModelRepository;

    public List<CarModel> find(Specification<CarModel> filter)
    {
        return carModelRepository.findAll()
                .stream()
                .filter(filter::isSatisfiedBy)
                .toList();
    }
}
