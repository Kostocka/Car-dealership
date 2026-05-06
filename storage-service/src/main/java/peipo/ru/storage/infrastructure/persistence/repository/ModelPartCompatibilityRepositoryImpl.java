package peipo.ru.storage.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.storage.domain.repository.ModelPartCompatibilityRepository;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.vo.CarModelId;
import peipo.ru.storage.infrastructure.persistence.entity.rules.ModelPartCompatibilityEntity;
import peipo.ru.storage.infrastructure.persistence.entity.rules.ModelPartCompatibilityId;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.ModelPartCompatibilityJpaRepository;

@Repository
@RequiredArgsConstructor
public class ModelPartCompatibilityRepositoryImpl implements ModelPartCompatibilityRepository
{
    private final ModelPartCompatibilityJpaRepository modelPartCompatibilityJpaRepository;

    @Override
    public boolean isCompatible(PartId partId, CarModelId carModelId)
    {
        return modelPartCompatibilityJpaRepository.existsById(
            new ModelPartCompatibilityId(partId.id(), carModelId.id())
        );
    }

    @Override
    public void add(PartId partId, CarModelId carModelId)
    {
        ModelPartCompatibilityEntity modelPartCompatibilityEntity = new ModelPartCompatibilityEntity();
        modelPartCompatibilityEntity.setId(new ModelPartCompatibilityId(partId.id(), carModelId.id()));

        modelPartCompatibilityJpaRepository.save(modelPartCompatibilityEntity);
    }
}
