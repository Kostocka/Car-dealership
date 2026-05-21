package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.StockAssemblyOrder;
import peipo.ru.storage.domain.repository.StockAssemblyOrderRepository;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.StockAssemblyOrderJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.StockAssemblyOrderMapper;

@Repository
@AllArgsConstructor
public class StockAssemblyOrderRepositoryImpl implements StockAssemblyOrderRepository
{
    private final StockAssemblyOrderJpaRepository jpa;
    private final StockAssemblyOrderMapper mapper;

    @Override
    public Optional<StockAssemblyOrder> findByOrderId(OrderId orderId)
    {
        return jpa.findAll().stream()
                .filter(e -> e.getSourceOrderId().equals(orderId.id()))
                .findFirst()
                .map(mapper::toDomain);
    }

    @Override
    public StockAssemblyOrder save(StockAssemblyOrder order)
    {
        return mapper.toDomain(jpa.save(mapper.toEntity(order)));
    }

    @Override
    public void delete(UUID id)
    {
        jpa.deleteById(id);
    }

    @Override
    public Optional<StockAssemblyOrder> findById(UUID id)
    {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<StockAssemblyOrder> findAll()
    {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }
}
