package org.kostocka.cardealership.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kostocka.cardealership.domain.vo.id.CarId;
import org.kostocka.cardealership.domain.vo.id.ClientId;
import org.kostocka.cardealership.domain.vo.id.TestDriveId;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TestDrive
{
    private final TestDriveId testDriveId;
    private final ClientId clientId;
    private final CarId carId;
    private final LocalDateTime startTime;
}
