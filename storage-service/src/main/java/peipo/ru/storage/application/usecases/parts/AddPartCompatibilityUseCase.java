package peipo.ru.storage.application.usecases.parts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.storage.domain.repository.PartCompatibilityRepository;
import peipo.ru.common.vo.id.PartId;

@Service
@AllArgsConstructor
public class AddPartCompatibilityUseCase
{
    private PartCompatibilityRepository partCompatibilityRepository;

    public void execute(PartId firstPartId, PartId secondPartId)
    {
        partCompatibilityRepository.addCompatibility(firstPartId, secondPartId);
    }
}
