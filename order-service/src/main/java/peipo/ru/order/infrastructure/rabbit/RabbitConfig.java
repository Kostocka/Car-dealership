package peipo.ru.order.infrastructure.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig
{
    public static final String EXCHANGE = "domain.exchange";

    public static final String ORDER_QUEUE = "order.queue";

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue orderQueue()
    {
        return new Queue(ORDER_QUEUE);
    }

    @Bean
    public Binding orderBinding(
            Queue orderQueue,
            TopicExchange exchange
    )
    {
        return BindingBuilder
                .bind(orderQueue)
                .to(exchange)
                .with("*.order.*");
    }
}
