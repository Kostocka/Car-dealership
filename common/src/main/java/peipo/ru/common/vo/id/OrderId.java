package peipo.ru.common.vo.id;

import java.util.UUID;

public record OrderId(UUID id)
{
    public static OrderId generate()
    {
        return new OrderId(UUID.randomUUID());
    }
}
