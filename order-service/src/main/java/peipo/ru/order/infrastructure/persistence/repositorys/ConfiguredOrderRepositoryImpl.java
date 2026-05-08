package peipo.ru.order.infrastructure.persistence.repositorys;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.order.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.order.domain.repository.ConfiguredOrderRepository;
import peipo.ru.order.infrastructure.persistence.jparepositorys.ConfiguredOrderJpaRepository;
import peipo.ru.order.infrastructure.persistence.mapper.order.ConfiguredOrderMapper;

@Repository
@RequiredArgsConstructor
public class ConfiguredOrderRepositoryImpl implements ConfiguredOrderRepository
{
    private final ConfiguredOrderJpaRepository configuredOrderJpaRepository;
    private final ConfiguredOrderMapper configuredOrderMapper;

    @Override
    public Optional<ConfiguredCarOrder> findById(OrderId id)
    {
        return configuredOrderJpaRepository.findById(id.id()).map(configuredOrderMapper::toDomain);
    }

    @Override
    public List<ConfiguredCarOrder> findByClientId(ClientId clientId)
    {
        return configuredOrderJpaRepository.findByClientId(clientId.id()).stream()
                .map(configuredOrderMapper::toDomain).toList();
    }

    @Override
    public List<ConfiguredCarOrder> findAll()
    {
        return configuredOrderJpaRepository.findAll()
            .stream()
            .map(configuredOrderMapper::toDomain)
            .toList();
    }

    @Override
    public ConfiguredCarOrder save(ConfiguredCarOrder entity)
    {
        return configuredOrderMapper
                .toDomain(configuredOrderJpaRepository.save(configuredOrderMapper.toEntity(entity)));
    }

    @Override
    public void delete(OrderId id)
    {
        configuredOrderJpaRepository.deleteById(id.id());
    }
}
