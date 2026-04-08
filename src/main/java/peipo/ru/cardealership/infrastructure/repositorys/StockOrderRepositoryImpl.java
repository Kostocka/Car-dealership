package peipo.ru.cardealership.infrastructure.repositorys;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.models.orders.StockCarOrder;
import peipo.ru.cardealership.domain.repository.StockOrderRepository;
import peipo.ru.cardealership.domain.vo.id.OrderId;
import peipo.ru.cardealership.infrastructure.jparepositorys.StockOrderJpaRepository;
import peipo.ru.cardealership.infrastructure.mapper.order.StockOrderMapper;

@Repository
@RequiredArgsConstructor
public class StockOrderRepositoryImpl implements StockOrderRepository
{
    private final StockOrderJpaRepository stockOrderJpaRepository;
    private final StockOrderMapper stockOrderMapper;

    @Override
    public Optional<StockCarOrder> findById(OrderId id)
    {
        return stockOrderJpaRepository.findById(id.id())
                .map(stockOrderMapper::toDomain);
    }

    @Override
    public List<StockCarOrder> findAll()
    {
        return stockOrderJpaRepository.findAll()
                .stream()
                .map(stockOrderMapper::toDomain)
                .toList();
    }

    @Override
    public void save(StockCarOrder entity)
    {
        stockOrderJpaRepository.save(stockOrderMapper.toEntity(entity));
    }

    @Override
    public void delete(OrderId id)
    {
        stockOrderJpaRepository.deleteById(id.id());
    }
}
