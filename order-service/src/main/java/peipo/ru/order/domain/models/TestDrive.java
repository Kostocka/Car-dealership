package peipo.ru.order.domain.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.ClientId;
import peipo.ru.order.domain.vo.id.TestDriveId;

@Getter
@AllArgsConstructor
public class TestDrive
{
    private final TestDriveId testDriveId;
    private final ClientId clientId;
    private final CarId carId;
    private final LocalDateTime startTime;
}
