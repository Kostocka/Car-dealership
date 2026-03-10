package org.kostocka.cardealership.domain.vo.id;

import java.util.UUID;

public record OrderId(UUID id)
{
    public static OrderId generate()
    {
        return new OrderId(UUID.randomUUID());
    }
}
