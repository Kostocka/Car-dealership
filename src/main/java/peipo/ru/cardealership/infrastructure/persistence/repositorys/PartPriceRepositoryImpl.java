package peipo.ru.cardealership.infrastructure.persistence.repositorys;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.repository.PartPriceRepository;
import peipo.ru.cardealership.domain.vo.Money;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.persistence.entity.stock.PartPriceEntity;
import peipo.ru.cardealership.infrastructure.persistence.jparepositorys.PartPriceJpaRepository;

@Repository
@RequiredArgsConstructor
public class PartPriceRepositoryImpl implements PartPriceRepository
{
    private final PartPriceJpaRepository partPriceJpaRepository;

    @Override
    public Optional<Money> getPartPrice(PartId id)
    {
        return partPriceJpaRepository.findById(id.id())
                .map(e -> new Money(e.getPrice()));
    }

    @Override
    public void setPartPrice(PartId id, Money price)
    {
        PartPriceEntity partPriceEntity = new PartPriceEntity();
        partPriceEntity.setPart(id.id());
        partPriceEntity.setPrice(price.value());

        partPriceJpaRepository.save(partPriceEntity);
    }
}
