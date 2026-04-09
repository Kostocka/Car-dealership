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
public class CreateConfiguredOrderRequest
{
    private UUID clientId;
    private CarConfigurationDto configuration;
}
