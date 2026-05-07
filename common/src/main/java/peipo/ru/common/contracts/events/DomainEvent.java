package peipo.ru.common.contracts.events;

import java.util.UUID;

public interface DomainEvent
{
    UUID aggregateId();

    String eventType();
}
