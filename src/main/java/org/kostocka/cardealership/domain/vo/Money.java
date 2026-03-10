package org.kostocka.cardealership.domain.vo;

import java.math.BigDecimal;

public record Money(BigDecimal value)
{
    public Money
    {
        if (value.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("Value must be greater than zero");
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
