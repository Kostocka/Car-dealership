package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.ConfiguredAssemblyOrder;
import peipo.ru.storage.domain.repository.ConfiguredAssemblyOrderRepository;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.ConfiguredAssemblyOrderJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.ConfiguredAssemblyOrderMapper;

@Repository
@AllArgsConstructor
public class ConfiguredAssemblyOrderRepositoryImpl implements ConfiguredAssemblyOrderRepository
{
    private final ConfiguredAssemblyOrderJpaRepository jpa;
    private final ConfiguredAssemblyOrderMapper mapper;

    @Override
    public Optional<ConfiguredAssemblyOrder> findByOrderId(OrderId orderId)
    {
        return jpa.findAll().stream()
                .filter(e -> e.getSourceOrderId().equals(orderId.id()))
                .findFirst()
                .map(mapper::toDomain);
    }

    @Override
    public ConfiguredAssemblyOrder save(ConfiguredAssemblyOrder order)
    {
        return mapper.toDomain(jpa.save(mapper.toEntity(order)));
    }

    @Override
    public void delete(UUID id)
    {
        jpa.deleteById(id);
    }

    @Override
    public Optional<ConfiguredAssemblyOrder> findById(UUID id)
    {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ConfiguredAssemblyOrder> findAll()
    {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }
}