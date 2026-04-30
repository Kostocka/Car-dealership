package peipo.ru.order.domain.vo.id;

import java.util.UUID;

public record ClientId(UUID id)
{
    public static ClientId generate()
    {
        return new ClientId(UUID.randomUUID());
    }
}
