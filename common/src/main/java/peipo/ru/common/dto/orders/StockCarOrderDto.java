package peipo.ru.common.dto.orders;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockCarOrderDto
{
    private UUID orderId;
    private UUID clientId;
    private UUID managerId;
    private UUID carId;
    private String state;
}
