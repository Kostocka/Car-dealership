package peipo.ru.storage.infrastructure.rabbit;

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

    public static final String STORAGE_QUEUE = "storage.queue";

    @Bean
    public TopicExchange domainExchange()
    {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue storageQueue()
    {
        return new Queue(STORAGE_QUEUE);
    }

    @Bean
    public Binding storageBinding(Queue storageQueue, TopicExchange exchange)
    {
        return BindingBuilder
                .bind(storageQueue)
                .to(exchange)
                .with("*.order.*");
    }
}
