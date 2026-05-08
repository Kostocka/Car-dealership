package peipo.ru.common.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.dto.ConfiguredCarRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateConfiguredOrderRequest
{
    private ConfiguredCarRequest configuration;
}
