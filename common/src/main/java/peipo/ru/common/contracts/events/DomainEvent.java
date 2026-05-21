package peipo.ru.common.contracts.events;

import java.util.UUID;

public abstract class DomainEvent
{
    public abstract UUID aggregateId();

    public abstract String eventType();
}
