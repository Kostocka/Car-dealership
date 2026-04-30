package peipo.ru.storage.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.repository.ModelPartCompatibilityRepository;
import peipo.ru.common.vo.id.PartId;

@Service
@AllArgsConstructor
public class AddModelPartCompatibilityUseCase
{
    private ModelPartCompatibilityRepository  modelPartCompatibilityRepository;

    public void execute(PartId partId, CarModelId  carModelId)
    {
        modelPartCompatibilityRepository.add(partId, carModelId);
    }
}
