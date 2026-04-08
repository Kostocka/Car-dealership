package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.exception.DomainValidationException;
import peipo.ru.cardealership.domain.exception.EntityNotFoundException;
import peipo.ru.cardealership.domain.repository.PartStockRepository;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.PartStockJpaRepository;
import peipo.ru.cardealership.infrastructure.persistence.entity.stock.PartStockEntity;

@Repository
@RequiredArgsConstructor
public class PartStockRepositoryImpl implements PartStockRepository
{
    private final PartStockJpaRepository partStockJpaRepository;

    @Override
    public int getQuantity(PartId partId)
    {
        return partStockJpaRepository.findById(partId.id())
                .map(PartStockEntity::getQuantity)
                .orElse(0);
    }

    @Override
    public void increase(PartId partId, int quantity)
    {
        PartStockEntity partStockEntity = partStockJpaRepository.findById(partId.id())
                .orElseGet(() ->
                {
                    PartStockEntity e = new PartStockEntity();
                    e.setQuantity(0);
                    e.setPart(partId.id());
                    return e;
                });
        partStockEntity.setQuantity(partStockEntity.getQuantity() + quantity);

        partStockJpaRepository.save(partStockEntity);
    }

    @Override
    public void decrease(PartId partId, int quantity)
    {
        PartStockEntity partStockEntity = partStockJpaRepository.findById(partId.id())
                .orElseThrow(() -> new EntityNotFoundException("Stock part not found"));

        if (partStockEntity.getQuantity() < quantity)
        {
            throw new DomainValidationException("Stock part not enough");
        }

        partStockEntity.setQuantity(partStockEntity.getQuantity() - quantity);

        partStockJpaRepository.save(partStockEntity);
    }
}
