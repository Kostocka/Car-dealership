package peipo.ru.common.dto.orders;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.dto.ConfiguredCarRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguredCarOrderDto
{
    private UUID orderId;
    private UUID clientId;
    private UUID managerId;
    private ConfiguredCarRequest configuration;
    private String state;
}
