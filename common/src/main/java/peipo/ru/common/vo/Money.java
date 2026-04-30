package peipo.ru.common.vo;

import java.math.BigDecimal;

public record Money(BigDecimal value)
{
    public Money
    {
        if (value.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("Value must be positive");
        }
    }

    public Money add(Money money)
    {
        return new Money(value.add(money.value));
    }

    public  Money sub(Money money)
    {
        return new Money(value.subtract(money.value));
    }
}
