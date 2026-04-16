package peipo.ru.cardealership.domain.vo.id;

import java.util.UUID;

public record PartId(UUID id)
{
    public static PartId generate()
    {
        return new PartId(UUID.randomUUID());
    }
}
