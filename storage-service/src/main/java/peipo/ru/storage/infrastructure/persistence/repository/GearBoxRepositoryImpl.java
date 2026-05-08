package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.parts.GearBox;
import peipo.ru.storage.domain.repository.PartRepository;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.parts.GearBoxJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.parts.GearBoxMapper;

@Repository
@RequiredArgsConstructor
public class GearBoxRepositoryImpl implements PartRepository<GearBox>
{

    private final GearBoxJpaRepository gearBoxJpaRepository;
    private final GearBoxMapper gearBoxMapper;

    @Override
    public Optional<GearBox> findById(PartId id)
    {
        return gearBoxJpaRepository.findById(id.id())
                .map(gearBoxMapper::toDomain);
    }

    @Override
    public List<GearBox> findAll()
    {
        return gearBoxJpaRepository.findAll()
                .stream()
                .map(gearBoxMapper::toDomain)
                .toList();
    }

    @Override
    public GearBox save(GearBox entity)
    {
        return gearBoxMapper.toDomain(gearBoxJpaRepository.save(gearBoxMapper.toEntity(entity)));
    }

    @Override
    public void delete(PartId id)
    {
        gearBoxJpaRepository.deleteById(id.id());
    }
}
