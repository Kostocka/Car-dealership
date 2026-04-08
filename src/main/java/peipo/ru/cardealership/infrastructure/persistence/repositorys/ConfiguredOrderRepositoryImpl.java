package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.models.orders.ConfiguredCarOrder;
import peipo.ru.cardealership.domain.repository.ConfiguredOrderRepository;
import peipo.ru.cardealership.domain.vo.id.OrderId;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.ConfiguredOrderJpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.mapper.order.ConfiguredOrderMapper;

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
    public List<ConfiguredCarOrder> findAll()
    {
        return configuredOrderJpaRepository.findAll()
            .stream()
            .map(configuredOrderMapper::toDomain)
            .toList();
    }

    @Override
    public void save(ConfiguredCarOrder entity)
    {
        configuredOrderJpaRepository.save(configuredOrderMapper.toEntity(entity));
    }

    @Override
    public void delete(OrderId id)
    {
        configuredOrderJpaRepository.deleteById(id.id());
    }
}
