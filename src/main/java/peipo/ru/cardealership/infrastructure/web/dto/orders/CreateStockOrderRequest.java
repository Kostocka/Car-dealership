package peipo.ru.cardealership.infrastructure.web.dto.orders;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStockOrderRequest
{
    private UUID clientId;
    private UUID carId;
}
