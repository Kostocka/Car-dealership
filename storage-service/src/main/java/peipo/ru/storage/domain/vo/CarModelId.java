package peipo.ru.storage.domain.vo;

import java.util.UUID;

public record CarModelId(UUID id)
{
    public static CarModelId generate()
    {
        return new CarModelId(UUID.randomUUID());
    }
}