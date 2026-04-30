package peipo.ru.order.infrastructure.web.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.order.infrastructure.web.dto.cars.CarConfigurationDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConfiguredOrderRequest
{
    private CarConfigurationDto configuration;
}
