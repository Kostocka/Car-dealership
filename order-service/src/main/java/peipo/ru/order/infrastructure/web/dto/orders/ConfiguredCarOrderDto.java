package peipo.ru.order.infrastructure.web.dto.orders;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.order.infrastructure.web.dto.cars.CarConfigurationDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguredCarOrderDto
{
    private UUID orderId;
    private UUID clientId;
    private UUID managerId;
    private CarConfigurationDto configuration;
    private String state;
}
