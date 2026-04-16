package peipo.ru.cardealership.domain.vo.id;

import java.util.UUID;

public record TestDriveId(UUID id)
{
    public static TestDriveId generate()
    {
        return new TestDriveId(UUID.randomUUID());
    }
}
