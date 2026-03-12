package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.PartId;

import java.util.Optional;

public interface PartPriceRepository
{
    Optional<Money> getPartPrice(PartId id);
}
