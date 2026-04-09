package peipo.ru.cardealership.infrastructure.web.dto.orders;

import lombok.*;

import java.util.UUID;

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
