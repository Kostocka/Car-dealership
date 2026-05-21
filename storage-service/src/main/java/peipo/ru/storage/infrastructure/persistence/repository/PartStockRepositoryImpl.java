package peipo.ru.storage.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.exception.DomainValidationException;
import peipo.ru.common.exception.EntityNotFoundException;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.domain.repository.PartStockRepository;
import peipo.ru.storage.infrastructure.persistence.entity.stock.PartStockEntity;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.PartStockJpaRepository;

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
