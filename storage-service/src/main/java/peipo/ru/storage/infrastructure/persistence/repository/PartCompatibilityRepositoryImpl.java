package peipo.ru.storage.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.repository.PartCompatibilityRepository;
import peipo.ru.storage.infrastructure.persistence.entity.rules.PartCompatibilityEntity;
import peipo.ru.storage.infrastructure.persistence.entity.rules.PartCompatibilityId;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.PartCompatibilityJpaRepository;

@Repository
@RequiredArgsConstructor
public class PartCompatibilityRepositoryImpl implements PartCompatibilityRepository
{
    private final PartCompatibilityJpaRepository partCompatibilityJpaRepository;

    @Override
    public boolean isCompatible(PartId firstPartId, PartId secondPartId)
    {
        return partCompatibilityJpaRepository.existsById(
                new PartCompatibilityId(firstPartId.id(), secondPartId.id())
        );
    }

    @Override
    public void addCompatibility(PartId firstPartId, PartId secondPartId)
    {
        PartCompatibilityEntity partCompatibilityEntity = new PartCompatibilityEntity();
        partCompatibilityEntity.setId(new PartCompatibilityId(firstPartId.id(), secondPartId.id()));

        partCompatibilityJpaRepository.save(partCompatibilityEntity);
    }
}
