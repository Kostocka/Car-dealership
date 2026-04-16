package peipo.ru.cardealership.domain.vo.id;

import java.util.UUID;

public record CarModelId(UUID id)
{
    public static CarModelId generate()
    {
        return new CarModelId(UUID.randomUUID());
    }
}