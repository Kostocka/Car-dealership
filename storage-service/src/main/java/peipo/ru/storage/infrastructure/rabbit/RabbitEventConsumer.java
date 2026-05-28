package peipo.ru.storage.infrastructure.rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderCancelledEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderCreatedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderPaidEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderCancelledEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderCreatedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderPaidEvent;

@Component
@AllArgsConstructor
public class RabbitEventConsumer
{
    private final ObjectMapper mapper;
    private final ApplicationEventPublisher publisher;

    @RabbitListener(queues = RabbitConfig.STORAGE_QUEUE)
    public void consume(String payload) throws Exception
    {
        JsonNode json = mapper.readTree(payload);

        String eventType = json.get("eventType").asText();

        JsonNode data = json.get("payload");

        Class<?> clazz = switch (eventType)
        {
            case "stock.order.created" -> StockCarOrderCreatedEvent.class;

            case "stock.order.paid" -> StockCarOrderPaidEvent.class;

            case "stock.order.cancelled" -> StockCarOrderCancelledEvent.class;

            case "configured.order.created" -> ConfiguredOrderCreatedEvent.class;

            case "configured.order.paid" -> ConfiguredOrderPaidEvent.class;

            case "configured.order.cancelled" -> ConfiguredOrderCancelledEvent.class;

            default -> throw new RuntimeException(
                    "Unknown event type " + eventType
            );
        };

        Object event = mapper.treeToValue(data, clazz);

        publisher.publishEvent(event);
    }
}
