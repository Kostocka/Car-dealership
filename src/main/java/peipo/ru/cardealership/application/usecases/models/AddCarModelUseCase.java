package peipo.ru.cardealership.application.usecases.models;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.repository.CarModelRepository;

@Service
@RequiredArgsConstructor
public class AddCarModelUseCase
{
    private final CarModelRepository carRepository;

    public void execute(CarModel carModel)
    {
        carRepository.save(carModel);
    }
}
