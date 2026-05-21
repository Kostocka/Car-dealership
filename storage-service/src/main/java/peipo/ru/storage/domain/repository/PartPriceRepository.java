package peipo.ru.storage.domain.repository;

import java.util.Optional;
import peipo.ru.common.vo.Money;
import peipo.ru.common.vo.id.PartId;

public interface PartPriceRepository
{
    Optional<Money> getPartPrice(PartId id);

    void setPartPrice(PartId id, Money price);
}
