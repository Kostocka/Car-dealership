package peipo.ru.cardealership.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.models.specifications.Specification;
import peipo.ru.cardealership.domain.repository.CarModelRepository;

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
