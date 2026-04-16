package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.repository.ModelPartCompatibilityRepository;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.rules.ModelPartCompatibilityEntity;
import peipo.ru.cardealership.infrastructure.persistence.entity.rules.ModelPartCompatibilityId;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.ModelPartCompatibilityJpaRepository;

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
