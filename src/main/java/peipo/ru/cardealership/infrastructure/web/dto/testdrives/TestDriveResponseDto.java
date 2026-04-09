package peipo.ru.cardealership.infrastructure.web.dto.testdrives;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDriveResponseDto
{
    private UUID testDriveId;
    private UUID clientId;
    private UUID carId;
    private LocalDateTime startTime;
}
