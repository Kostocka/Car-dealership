package peipo.ru.storage.infrastructure.rabbit;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import peipo.ru.storage.infrastructure.outbox.entity.OutboxEventEntity;
import peipo.ru.storage.infrastructure.outbox.repository.OutboxJpaRepository;

@Component
@RequiredArgsConstructor
public class RabbitEventPublisher
{
    private final OutboxJpaRepository jpa;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 2000)
    @Transactional
    public void publish()
    {
        List<OutboxEventEntity> events = jpa.findByProcessedFalse();

        for (OutboxEventEntity e : events)
        {
            rabbitTemplate.convertAndSend(
                    "domain.exchange",
                    e.getEventType(),
                    e.getPayload()
            );

            e.setProcessed(true);
            jpa.save(e);
        }
    }
}
