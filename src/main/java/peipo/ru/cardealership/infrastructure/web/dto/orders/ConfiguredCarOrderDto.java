package peipo.ru.cardealership.infrastructure.web.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarConfigurationDto;

import java.util.UUID;

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
