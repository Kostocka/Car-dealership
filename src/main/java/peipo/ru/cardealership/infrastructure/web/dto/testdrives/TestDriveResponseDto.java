package peipo.ru.cardealership.infrastructure.web.dto.testdrives;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
