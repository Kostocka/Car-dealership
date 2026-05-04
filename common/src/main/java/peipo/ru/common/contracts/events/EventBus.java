package peipo.ru.common.contracts.events;

public interface EventBus
{
    void publish(DomainEvent event);
}