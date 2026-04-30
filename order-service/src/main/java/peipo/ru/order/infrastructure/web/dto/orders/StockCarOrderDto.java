package peipo.ru.order.infrastructure.web.dto.orders;

import java.util.UUID;
import lombok.*;

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
