package org.kostocka.cardealership.domain.repository;

import java.util.Optional;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.PartId;

public interface PartPriceRepository
{
    Optional<Money> getPartPrice(PartId id);

    void setPartPrice(PartId id, Money price);
}
