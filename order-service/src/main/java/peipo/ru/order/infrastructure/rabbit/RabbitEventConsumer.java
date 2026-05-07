package peipo.ru.order.infrastructure.rabbit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderDeliveredEvent;
import peipo.ru.common.contracts.events.orders.configured.ConfiguredOrderRejectedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderAcceptedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarOrderRejectedEvent;
import peipo.ru.common.contracts.events.orders.stock.StockCarReadyForPickupEvent;

@Component
@AllArgsConstructor
public class RabbitEventConsumer
{
    private final ObjectMapper mapper;
    private final ApplicationEventPublisher publisher;

    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE)
    public void consume(String payload) throws Exception
    {
        JsonNode json = mapper.readTree(payload);

        String eventType =
                json.get("eventType").asText();

        Class<?> clazz = switch (eventType)
        {
            case "stock.order.accepted" ->
                    StockCarOrderAcceptedEvent.class;

            case "stock.order.rejected" ->
                    StockCarOrderRejectedEvent.class;

            case "stock.order.ready_for_pickup" ->
                    StockCarReadyForPickupEvent.class;

            case "configured.order.accepted" ->
                    ConfiguredOrderAcceptedEvent.class;

            case "configured.order.rejected" ->
                    ConfiguredOrderRejectedEvent.class;

            case "configured.order.delivered" ->
                    ConfiguredOrderDeliveredEvent.class;

            default -> throw new RuntimeException(
                    "Unknown event type " + eventType
            );
        };

        Object event =
                mapper.readValue(payload, clazz);

        publisher.publishEvent(event);
    }
}