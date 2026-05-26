package peipo.ru.order.infrastructure.grpc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "grpc.storage")
public class GrpcStorageProperties
{
    private long timeoutMs;
}
