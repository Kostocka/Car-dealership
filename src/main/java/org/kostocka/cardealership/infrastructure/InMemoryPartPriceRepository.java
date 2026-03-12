package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.repository.PartPriceRepository;
import org.kostocka.cardealership.domain.vo.Money;
import org.kostocka.cardealership.domain.vo.id.PartId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPartPriceRepository implements PartPriceRepository
{
    private final Map<PartId, Money> prices = new HashMap<>();

    @Override
    public Optional<Money> getPartPrice(PartId id) {
        return Optional.ofNullable(prices.get(id));
    }
}
