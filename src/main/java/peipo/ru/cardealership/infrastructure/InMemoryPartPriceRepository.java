package peipo.ru.cardealership.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import peipo.ru.cardealership.domain.repository.PartPriceRepository;
import peipo.ru.cardealership.domain.vo.Money;
import peipo.ru.cardealership.domain.vo.id.PartId;

public class InMemoryPartPriceRepository implements PartPriceRepository
{
    private final Map<PartId, Money> prices = new HashMap<>();

    @Override
    public Optional<Money> getPartPrice(PartId id)
    {
        return Optional.ofNullable(prices.get(id));
    }

    @Override
    public void setPartPrice(PartId id, Money price)
    {
        prices.put(id, price);
    }
}
