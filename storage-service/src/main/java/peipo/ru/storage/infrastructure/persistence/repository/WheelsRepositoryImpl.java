package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.models.parts.Wheels;
import peipo.ru.storage.domain.repository.PartRepository;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.parts.WheelsJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.parts.WheelsMapper;

@Repository
@RequiredArgsConstructor
public class WheelsRepositoryImpl implements PartRepository<Wheels>
{

    private final WheelsJpaRepository wheelsJpaRepository;
    private final WheelsMapper wheelsMapper;

    @Override
    public Optional<Wheels> findById(PartId id)
    {
        return wheelsJpaRepository.findById(id.id())
                .map(wheelsMapper::toDomain);
    }

    @Override
    public List<Wheels> findAll()
    {
        return wheelsJpaRepository.findAll()
                .stream()
                .map(wheelsMapper::toDomain)
                .toList();
    }

    @Override
    public Wheels save(Wheels entity)
    {
        return wheelsMapper.toDomain(wheelsJpaRepository.save(wheelsMapper.toEntity(entity)));
    }

    @Override
    public void delete(PartId id)
    {
        wheelsJpaRepository.deleteById(id.id());
    }
}
