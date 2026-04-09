package peipo.ru.cardealership.infrastructure.web.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStockOrderRequest
{
    private UUID clientId;
    private UUID carId;
}
