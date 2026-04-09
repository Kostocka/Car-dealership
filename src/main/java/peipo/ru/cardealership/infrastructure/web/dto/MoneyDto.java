package peipo.ru.cardealership.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.Money;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDto
{
    private BigDecimal partPrice;

    public Money toMoney()
    {
        return new Money(partPrice);
    }

    public static MoneyDto fromMoney(Money money)
    {
        return new MoneyDto(money.value());
    }
}
