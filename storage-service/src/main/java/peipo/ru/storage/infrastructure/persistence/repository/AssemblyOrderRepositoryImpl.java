package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.AssemblyOrder;
import peipo.ru.storage.domain.repository.AssemblyOrderRepository;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.AssemblyOrderJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.AssemblyOrderMapper;

@Repository
@AllArgsConstructor
public class AssemblyOrderRepositoryImpl implements AssemblyOrderRepository
{
    private final AssemblyOrderJpaRepository jpaRepository;
    private final AssemblyOrderMapper mapper;

    @Override
    public AssemblyOrder save(AssemblyOrder order)
    {
        return mapper.toDomain(
                jpaRepository.save(mapper.toEntity(order))
        );
    }

    @Override
    public void delete(UUID id)
    {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<AssemblyOrder> findById(UUID id)
    {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<AssemblyOrder> findAll()
    {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain).toList();
    }

    @Override
    public Optional<AssemblyOrder> findByOrderId(OrderId orderId)
    {
        return jpaRepository.findBySourceOrderId(orderId.id())
                .map(mapper::toDomain);
    }
}