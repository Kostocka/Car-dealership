package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.models.parts.Wheels;
import peipo.ru.cardealership.domain.repository.PartRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.WheelsJpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.mapper.parts.WheelsMapper;

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
    public void save(Wheels entity)
    {
        wheelsJpaRepository.save(wheelsMapper.toEntity(entity));
    }

    @Override
    public void delete(PartId id)
    {
        wheelsJpaRepository.deleteById(id.id());
    }
}
