package peipo.ru.storage.application.usecases.models;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.CarModelRepository;

@Service
@RequiredArgsConstructor
public class AddCarModelUseCase
{
    private final CarModelRepository carRepository;

    public CarModel execute(CarModel carModel)
    {
        return carRepository.save(carModel);
    }
}
