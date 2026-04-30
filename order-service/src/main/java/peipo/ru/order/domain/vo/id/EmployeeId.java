package peipo.ru.order.domain.vo.id;

import java.util.UUID;

public record EmployeeId(UUID id)
{
    public static EmployeeId generate()
    {
        return new EmployeeId(UUID.randomUUID());
    }
}