package peipo.ru.order.infrastructure.rabbit;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventEnvelope
{
    private final String eventType;
    private final UUID aggregateId;
    private final Object payload;
}
