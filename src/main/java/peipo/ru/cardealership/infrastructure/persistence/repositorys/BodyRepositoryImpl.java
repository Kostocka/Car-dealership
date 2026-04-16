package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.models.parts.Body;
import peipo.ru.cardealership.domain.repository.PartRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.parts.BodyJpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.mapper.parts.BodyMapper;

@Repository
@RequiredArgsConstructor
public class BodyRepositoryImpl implements PartRepository<Body>
{

    private final BodyJpaRepository bodyJpaRepository;
    private final BodyMapper bodyMapper;

    @Override
    public Optional<Body> findById(PartId id)
    {
        return bodyJpaRepository.findById(id.id())
                .map(bodyMapper::toDomain);
    }

    @Override
    public List<Body> findAll()
    {
        return bodyJpaRepository.findAll()
                .stream()
                .map(bodyMapper::toDomain)
                .toList();
    }

    @Override
    public Body save(Body entity)
    {
        return bodyMapper.toDomain(bodyJpaRepository.save(bodyMapper.toEntity(entity)));
    }

    @Override
    public void delete(PartId id)
    {
        bodyJpaRepository.deleteById(id.id());
    }
}
