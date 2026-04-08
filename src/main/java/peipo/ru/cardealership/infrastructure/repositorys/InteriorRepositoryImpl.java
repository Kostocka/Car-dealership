package peipo.ru.cardealership.infrastructure.repositorys;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.models.parts.Interior;
import peipo.ru.cardealership.domain.repository.PartRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.jparepositorys.parts.InteriorJpaRepository;
import peipo.ru.cardealership.infrastructure.mapper.parts.InteriorMapper;

@Repository
@RequiredArgsConstructor
public class InteriorRepositoryImpl implements PartRepository<Interior>
{

    private final InteriorJpaRepository interiorJpaRepository;
    private final InteriorMapper interiorMapper;

    @Override
    public Optional<Interior> findById(PartId id)
    {
        return interiorJpaRepository.findById(id.id())
                .map(interiorMapper::toDomain);
    }

    @Override
    public List<Interior> findAll()
    {
        return interiorJpaRepository.findAll()
                .stream()
                .map(interiorMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Interior entity)
    {
        interiorJpaRepository.save(interiorMapper.toEntity(entity));
    }

    @Override
    public void delete(PartId id)
    {
        interiorJpaRepository.deleteById(id.id());
    }
}
