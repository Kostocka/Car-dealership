package peipo.ru.cardealership.application.usecases.models;

import lombok.AllArgsConstructor;
import peipo.ru.cardealership.domain.models.CarModel;
import peipo.ru.cardealership.domain.repository.CarModelRepository;

@AllArgsConstructor
public class AddCarModelUseCase
{
    private CarModelRepository carRepository;

    public void execute(CarModel carModel)
    {
        carRepository.save(carModel);
    }
}
