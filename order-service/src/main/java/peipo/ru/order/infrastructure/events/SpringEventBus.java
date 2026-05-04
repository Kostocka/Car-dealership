package peipo.ru.order.infrastructure.events;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.DomainEvent;
import peipo.ru.common.contracts.events.EventBus;

@Component
@AllArgsConstructor
public class SpringEventBus implements EventBus
{
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(DomainEvent event)
    {
        publisher.publishEvent(event);
    }
}
