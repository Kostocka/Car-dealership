package peipo.ru.order.infrastructure.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class EventEnvelope
{
    private final String eventType;
    private final UUID aggregateId;
    private final Object payload;
}
