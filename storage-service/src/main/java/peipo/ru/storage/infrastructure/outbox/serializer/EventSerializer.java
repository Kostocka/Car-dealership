package peipo.ru.storage.infrastructure.outbox.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.DomainEvent;

@Component
@RequiredArgsConstructor
public class EventSerializer
{
    private final ObjectMapper objectMapper;

    public String toJson(Object event)
    {
        try
        {
            return objectMapper.writeValueAsString(event);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}