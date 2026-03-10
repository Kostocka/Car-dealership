package org.kostocka.cardealership.domain.vo.id;

import java.util.UUID;

public record CarId(UUID id)
{
    public static CarId generate()
    {
        return new CarId(UUID.randomUUID());
    }
}