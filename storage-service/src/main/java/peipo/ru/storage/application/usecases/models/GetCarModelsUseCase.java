package peipo.ru.storage.application.usecases.models;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.CarModelRepository;

@Service
@AllArgsConstructor
public class GetCarModelsUseCase
{
    private CarModelRepository carModelRepository;

    public List<CarModel> execute()
    {
        return carModelRepository.findAll();
    }
}
