package peipo.ru.cardealership.domain.repository;

import java.util.Optional;
import peipo.ru.cardealership.domain.vo.Money;
import peipo.ru.cardealership.domain.vo.id.PartId;

public interface PartPriceRepository
{
    Optional<Money> getPartPrice(PartId id);

    void setPartPrice(PartId id, Money price);
}
