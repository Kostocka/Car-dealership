package peipo.ru.order.infrastructure.grpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class StorageInstanceResolver
{
    private final DiscoveryClient discoveryClient;
    private final GrpcStorageProperties props;
    private volatile ServiceInstance cached;
    private volatile long lastFetchTime;

    public ServiceInstance resolve()
    {
        long now = System.currentTimeMillis();

        if (cached != null && (now - lastFetchTime) < props.getInstanceCacheTtlMs())
        {
            return cached;
        }

        var instances = discoveryClient.getInstances("storage-service");

        if (instances.isEmpty())
        {
            log.warning("storage-service not found in discovery");
            return null;
        }

        cached = instances.getFirst();
        lastFetchTime = now;

        return cached;
    }
}