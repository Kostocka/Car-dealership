package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.models.parts.Engine;
import peipo.ru.cardealership.domain.repository.PartRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.EngineJpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.mapper.parts.EngineMapper;

@Repository
@RequiredArgsConstructor
public class EngineRepositoryImpl implements PartRepository<Engine>
{

    private final EngineJpaRepository engineJpaRepository;
    private final EngineMapper engineMapper;

    @Override
    public Optional<Engine> findById(PartId id)
    {
        return engineJpaRepository.findById(id.id())
                .map(engineMapper::toDomain);
    }

    @Override
    public List<Engine> findAll()
    {
        return engineJpaRepository.findAll()
                .stream()
                .map(engineMapper::toDomain)
                .toList();
    }

    @Override
    public Engine save(Engine entity)
    {
        return engineMapper.toDomain(engineJpaRepository.save(engineMapper.toEntity(entity)));
    }

    @Override
    public void delete(PartId id)
    {
        engineJpaRepository.deleteById(id.id());
    }
}
