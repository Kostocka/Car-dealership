package peipo.ru.common.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.vo.Money;

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
